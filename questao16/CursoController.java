package questao16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    public void adicionarCurso(@RequestBody Curso curso) {
        cursoService.adicionarCurso(curso);
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

    @PutMapping
    public void atualizarCurso(@RequestBody Curso curso) {
        cursoService.atualizarCurso(curso);
    }

    @DeleteMapping("/{id}")
    public void excluirCurso(@PathVariable Long id) {
        cursoService.excluirCurso(id);
    }
}