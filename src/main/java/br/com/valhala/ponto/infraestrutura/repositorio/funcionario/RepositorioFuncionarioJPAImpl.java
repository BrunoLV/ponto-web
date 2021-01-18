package br.com.valhala.ponto.infraestrutura.repositorio.funcionario;

import br.com.valhala.ponto.dominio.excecoes.ModeloNaoEncontradoException;
import br.com.valhala.ponto.dominio.funcionario.modelo.Funcionario;
import br.com.valhala.ponto.dominio.funcionario.repositorio.RepositorioFuncionario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class RepositorioFuncionarioJPAImpl implements RepositorioFuncionario {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Funcionario salva(Funcionario funcionario) {
        if (exists(funcionario.getUuid())) {
            em.merge(funcionario);
        } else {
            em.persist(funcionario);
        }
        return funcionario;
    }

    @Override
    public void exclui(String uuid) {
        Funcionario funcionario = buscaPorId(uuid);
        em.remove(funcionario);
    }

    @Override
    public Funcionario buscaPorId(String uuid) {
        try {
            return em.createNamedQuery("Funcionario.buscaPorUuid", Funcionario.class).setParameter("uuid", uuid).getSingleResult();
        } catch (NoResultException e) {
            throw new ModeloNaoEncontradoException("Modelo não encontrado.");
        }
    }

    @Override
    public Collection<Funcionario> buscaTodos() {
        return em.createNamedQuery("Funcionario.buscaTodos", Funcionario.class).getResultList();
    }

    @Override
    public long contagem() {
        return (long) em.createNamedQuery("Funcionario.contagem").getSingleResult();

    }

    private boolean exists(final String uuid) {
        return (boolean) em.createNamedQuery("Funcionario.existeUuid").setParameter("uuid", uuid).getSingleResult();
    }

}