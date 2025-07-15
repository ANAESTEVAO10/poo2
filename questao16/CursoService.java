package questao16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public void adicionarCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public void atualizarCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    public void excluirCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}