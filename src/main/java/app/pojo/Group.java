package app.pojo;

import app.enums.EnumGroups;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Group implements Pojo<Group> {

  private EnumGroups enumGroups;
  private String name;

  public String getName() {
    return name;
  }

  @Override
  public Group setField(String field, Object value) {
    switch (field)  {
      case "enumGroups":  {
        this.enumGroups = (EnumGroups) value;
        break;
      }
      case "name":  {
        this.name = (String) value;
        break;
      }
    }
    return this;
  }
}
