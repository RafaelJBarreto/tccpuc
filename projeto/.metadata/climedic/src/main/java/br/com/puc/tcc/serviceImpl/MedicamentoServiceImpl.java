package br.com.puc.tcc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.dominio.Medicamento;
import br.com.puc.tcc.integracao.spec.MedicamentoDao;
import br.com.puc.tcc.service.MedicamentoService;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

	@Autowired
	private MedicamentoDao medicamentoDao;

	@Override
	public void salvarMedicamento(Medicamento medicamento) {
		medicamentoDao.salvarMedicamento(medicamento);
	}

	@Override
	public void editarMedicamento(Medicamento medicamento) {
		medicamentoDao.editarMedicamento(medicamento);		
	}

	@Override
	public void deletarMedicamento(Integer idMedicamento) {
		medicamentoDao.deletarMedicamento(idMedicamento);
	}

	@Override
	public List<Medicamento> getMedicamentos() {
		return medicamentoDao.getMedicamentos();
	}

	@Override
	public Medicamento getMedicamento(Integer idMedicamento) {
		return medicamentoDao.getMedicamento(idMedicamento);
	};

}
