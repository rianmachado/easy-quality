/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.read.in.query;
import java.util.List;

import br.com.easy.quality.event.query.Query;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;

public class ListAllInspecaoQuery implements Query {

    private List<InspecaoDTO> result;

    public List<InspecaoDTO> getResult() {
        return result;
    }

    public void setResult(List<InspecaoDTO> result) {
        this.result = result;
    }
}
