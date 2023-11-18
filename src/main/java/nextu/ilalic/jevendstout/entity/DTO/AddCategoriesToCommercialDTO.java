package nextu.ilalic.jevendstout.entity.DTO;

import java.util.List;

public class AddCategoriesToCommercialDTO {
    private Long idCommercial;

    private List<Long> categoriesIdList;

    public Long getIdCommercial() {
        return idCommercial;
    }

    public void setIdCommercial(Long idCommercial) {
        this.idCommercial = idCommercial;
    }

    public List<Long> getCategoriesIdList() {
        return categoriesIdList;
    }

    public void setCategoriesIdList(List<Long> categoriesIdList) {
        this.categoriesIdList = categoriesIdList;
    }
}
