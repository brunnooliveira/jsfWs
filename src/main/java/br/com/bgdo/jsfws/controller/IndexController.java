package br.com.bgdo.jsfws.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import br.com.bgdo.jsfws.dictionary.Definition;
import br.com.bgdo.jsfws.dictionary.DictService;
import br.com.bgdo.jsfws.dictionary.DictServiceSoap;
import br.com.bgdo.jsfws.dictionary.WordDefinition;
import br.com.bgdo.jsfws.handler.TestHandler;

@ManagedBean
@ViewScoped
public class IndexController implements Serializable {

    private static final long serialVersionUID = 1L;

    private String searchWord;

    private List<Definition> definitions;

    public void search() {

        DictService service = new DictService();
        DictServiceSoap port = service.getDictServiceSoap();
        ((BindingProvider)port).getRequestContext().put("TESTE_PARAM", "testando parametro handler");
        
        Binding binding = ((BindingProvider)port).getBinding();
        List<Handler> handlerList = binding.getHandlerChain();
        handlerList.add(new TestHandler());
        binding.setHandlerChain(handlerList);
        
        WordDefinition define = port.define(searchWord);

        if (define != null && define.getDefinitions() != null) {
            definitions = define.getDefinitions().getDefinition();
        }
        else
        {
            definitions = new ArrayList<Definition>();
        }

		// for (Definition def : definitions) {
		// System.out.println(def.getWordDefinition());
		// }

    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

}
