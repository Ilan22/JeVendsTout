package nextu.ilalic.jevendstout.entity.DTO;

import java.util.List;

public class ValiderMultipleDevisDTO {
    List<Long> idDevisList;

    Long idCommercial;

    public List<Long> getIdDevisList() {
        return idDevisList;
    }

    public void setIdDevisList(List<Long> idDevisList) {
        this.idDevisList = idDevisList;
    }

    public Long getIdCommercial() {
        return idCommercial;
    }

    public void setIdCommercial(Long idCommercial) {
        this.idCommercial = idCommercial;
    }
}
