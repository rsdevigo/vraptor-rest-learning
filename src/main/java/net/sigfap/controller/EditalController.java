package net.sigfap.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.validation.Valid;

import net.sigfap.core.domain.Edital;
import net.sigfap.dao.EditalDAO;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.converter.DateConverter;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.view.Results;

@Controller
public class EditalController {
	@Inject
	private EditalDAO editalDAO;

	@Inject
	private Environment enviroment;

	@Inject
	private Result result;
	
	
	@Get
	@Path("/editais")
	public void listar() {
		result.use(Results.json())
         .withoutRoot()
         .from(editalDAO.findAll())
         .serialize();
	}
	
	@Consumes("application/json")
	@Post
	@Path("/editais")
	public void criar(Edital edital) {
		/* TODO - Validate Edital */
		editalDAO.persist(edital);
		
		result.use(Results.json())
        .withoutRoot()
        .from(edital)
        .serialize();
		
	}
	
	@Consumes("application/json")
	@Put
	@Path("/editais")
	public void editar(Edital edital) {
		/* TODO - Validate Edital */
		editalDAO.update(edital);
		
		result.use(Results.json())
        .withoutRoot()
        .from(edital)
        .serialize();
		
	}
	
	@Delete("/editais/{edital.id}")
	public void deletar(Edital edital) {
		/* TODO - Validate Edital */
		
		edital = editalDAO.findById(edital.getId());
		
		editalDAO.delete(edital);
		
		result.use(Results.json())
        .withoutRoot()
        .from(edital)
        .serialize();
		
	}
	
	

}
