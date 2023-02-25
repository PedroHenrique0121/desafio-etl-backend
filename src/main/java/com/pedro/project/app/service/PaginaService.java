package com.pedro.project.app.service;

import com.pedro.project.app.model.Pagina;
import com.pedro.project.app.modelmapper.conversores.PaginaConversor;
import com.pedro.project.app.repository.PaginaRepository;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaginaService {

    private PaginaRepository paginaRepository;
    private PaginaConversor paginaConversor;

    public Pagina salvarConteudoOriginal(Pagina pagina) {
        return this.paginaRepository.save(pagina);
    }

    public Pagina salvarConteudoModificado(Pagina pagina, Integer id) {
        return this.paginaRepository.findById(id)
                .map(obj -> {
                    obj.setConteudoModificado(pagina.getConteudoModificado());
                    return this.paginaRepository.save(obj);
                })
                .orElseThrow(() -> {
                            return new RuntimeException("Não foi possivel salvar o conteudo modificado");
                        }
                );
    }

    public Pagina retornarPagina(Integer id) {
        return this.paginaRepository.findById(id)
                .orElseThrow(
                        () -> {
                            return new RuntimeException("pagina não encontrada");
                        }
                );
    }

    public List<Pagina> retornarTodasPaginas() {
        return this.paginaRepository.findAll();
    }

    public String modificarPaginas() {
        List<Pagina> paginas = this.retornarTodasPaginas();
        Integer contador = 0;
        for (Pagina pagina : paginas) {

            if (pagina.isConteudoProcessado() == false) {
                // instrução para atribuir conteudo html a ser modificado ao Jsoup
                Document doc = Jsoup.parse(pagina.getConteudoOriginal());

                //  query css para identificar tags <a href="//jirac>
                Elements elementos = doc.select("a[href^=https://jirac]");

                // bloco para iterar sobre tags identificadas
                for (Element elemento : elementos) {
                    //extração de atributos de cada tag identificada
                    String titulo = elemento.text();
                    String id = this.extrairId(elemento.attr("href"));

                    // string  montada com os atributos extraidos de cada tag
                    String novaTag = "<ac:link><ri:page ri:content-title='" + titulo + "'/><ac:plain-text-link-body>" + titulo + ":" + id + "</ac:plain-text-link-body></ac:link>";

                    // Tenho que inserir   tag montada em um documento com formato HTML para que o jsoup a possa manipular
                    //criei esse documento como corpo HTML chamado tag
                    //agora a tag está dentro de um corpo html, depois tenho que extrair
                    Document tag = Jsoup.parse(novaTag);

                    // Seleciona  e extrai a tag ac\\:link que foi embutida no documento 'tag'
                    Element acLink = tag.select("ac\\:link").first();
                    elemento.replaceWith(acLink);

                }

                // instruções para salvar o conteúdo modificado
                String novoHTML= doc.select("body").html();
                pagina.setConteudoModificado(novoHTML);
                pagina.setConteudoProcessado(true);
                this.salvarConteudoModificado(pagina, pagina.getId());
                contador++;
            }
        }
        return "Processo foi ralizado com sucesso em : " + contador + " paginas.";
    }

    public String extrairId(String href) {
        if (href.contains("pageId")) {
            return href.substring(href.indexOf("=") + 1, href.indexOf("&"));
        } else {
            return "";
        }
    }
}
