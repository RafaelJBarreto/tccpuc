package br.com.puc.tcc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.dominio.Exame;
import br.com.puc.tcc.integracao.spec.ExameDao;
import br.com.puc.tcc.service.ExameService;

@Service
public class ExameServiceImpl implements ExameService {

	@Autowired
	private ExameDao exameDao;

	@Override
	public List<Exame> getExames() {
		return exameDao.getExamess();
	}
}
