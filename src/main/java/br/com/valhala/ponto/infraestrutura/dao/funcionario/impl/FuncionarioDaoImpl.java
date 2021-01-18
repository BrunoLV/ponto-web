package br.com.valhala.ponto.infraestrutura.dao.funcionario.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import br.com.valhala.ponto.aplicacao.dtos.funcionario.FiltroGridDTO;
import br.com.valhala.ponto.aplicacao.dtos.funcionario.FuncionarioDataGridDTO;
import br.com.valhala.ponto.aplicacao.dtos.funcionario.NomeDTO;
import br.com.valhala.ponto.dominio.funcionario.modelo.CPF;
import br.com.valhala.ponto.dominio.funcionario.modelo.Funcionario;
import br.com.valhala.ponto.dominio.funcionario.modelo.Nome;
import br.com.valhala.ponto.infraestrutura.dao.funcionario.FuncionarioDao;

@Component
public class FuncionarioDaoImpl implements FuncionarioDao {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public Collection<FuncionarioDataGridDTO> obtemDadosDataGrid(FiltroGridDTO filtro) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
		Root<Funcionario> root = criteriaQuery.from(Funcionario.class);
		criteriaQuery.select(criteriaBuilder.tuple(root.get("uuid"), root.get("nome"), root.get("nomeMae"),
				root.get("nomePai"), root.get("dataNascimento"), root.get("dataAdmissao")));

		List<Predicate> predicados = criaPredicadosAPartirDoFiltro(filtro, criteriaBuilder, root);

		if (!predicados.isEmpty()) {
			Predicate[] array = predicados.toArray(new Predicate[predicados.size()]);
			Predicate whereClause = criteriaBuilder.and(array);
			criteriaQuery.where(whereClause);
		}

		TypedQuery<Tuple> query = em.createQuery(criteriaQuery);

		List<Tuple> tuplas = query.getResultList();

		List<FuncionarioDataGridDTO> dados = tuplas.stream()
				.map(t -> new FuncionarioDataGridDTO(t.get(0, String.class), NomeDTO.aPartirDe(t.get(1, Nome.class)),
						NomeDTO.aPartirDe(t.get(2, Nome.class)), NomeDTO.aPartirDe(t.get(3, Nome.class)),
						t.get(4, LocalDate.class), t.get(5, LocalDate.class)))
				.collect(Collectors.toList());

		return dados;

	}

	private List<Predicate> criaPredicadosAPartirDoFiltro(FiltroGridDTO filtro, CriteriaBuilder cb,
			Root<Funcionario> root) {
		List<Predicate> predicados = new ArrayList<>();
		if (filtro.getUuid() != null) {
			predicados.add(cb.equal(root.get("uuid"), filtro.getUuid()));
		}
		if (filtro.getNome() != null) {
			predicados.add(cb.equal(root.get("nome"), filtro.getNome()));
		}
		if (filtro.getNomeMae() != null) {
			predicados.add(cb.equal(root.get("nomeMae"), filtro.getNomeMae()));
		}
		if (filtro.getNomePai() != null) {
			predicados.add(cb.equal(root.get("nomePai"), filtro.getNomePai()));
		}
		if (filtro.getDataNascimento() != null) {
			predicados.add(cb.equal(root.get("dataNascimento"), filtro.getDataNascimento()));
		}
		if (filtro.getDataAdmissao() != null) {
			predicados.add(cb.equal(root.get("dataAdmissao"), filtro.getDataAdmissao()));
		}
		return predicados;
	}

	@Override
	public Boolean verificaExistenciaFuncionarioComUuid(String uuid) {
		return (boolean) em.createNamedQuery("Funcionario.existeUuid").setParameter("uuid", uuid).getSingleResult();
	}

	@Override
	public Boolean verificaExistenciaFuncionarioComCpf(CPF cpf) {
		return (boolean) em.createNamedQuery("Funcionario.existeCpf").setParameter("cpf", cpf).getSingleResult();
	}

	@Override
	public Boolean verificaExistenciaFuncionarioComCpfEUuidDiferente(CPF cpf, String uuid) {
		return (boolean) em.createNamedQuery("Funcionario.existeCpfComUuidDiferente").setParameter("cpf", cpf)
				.setParameter("uuid", uuid).getSingleResult();
	}

}
