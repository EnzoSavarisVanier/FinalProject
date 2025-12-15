package org.example;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
public class Department {
    private String departmentId;
    @Setter
    private String departmentName;
    private static int nextId = 1;

    public static boolean isDepartmentNameValid(String departmentName) {
        if (departmentName == null || departmentName.isEmpty())
        {
            return false;
        }
        for (int i = 0; i < departmentName.length(); i++) {
            char character = departmentName.charAt(i);
            if (!Character.isLetter(character) && character != ' ')
            {
                return false;
            }
        }
        return true;
    }

    public Department(String departmentName) {
        if (isDepartmentNameValid(departmentName))
        {
            this.departmentName = departmentName;
            this.departmentId = String.format("D%02d", nextId++);
        }
        else
        {
            this.departmentName = null;
            this.departmentId = null;
        }
    }
}
