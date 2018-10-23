package com.ljkj.qxn.wisdomsitepro.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProjectOverViewInfo {

    @SerializedName("projectBase")
    public ProjectBase base = new ProjectBase();

    public List<Unit> unitList = new ArrayList<>();

    public UnitQualifications unitQualifications = new UnitQualifications();


    public List<Person> unitPersonList = new ArrayList<>();

    public static class ProjectBase {
        public String projId;
        public String projName;
        public String projCode;
        public String totalArea;
        public String numberOfFloors;
        public String undergroundStoreys;
        public String constructionCost;
        public String buildingHeight;
        public String structureTypeName;
        public String projAddress;
        public String projTypeName;
        public String baseTypeName;
        public String projCategoryParentName;
        public String projCategoryName;
        public String buildingTypeName;
        public String safeGoals;
        public String qualityGoals;
        public String startDate;
        public String endDate;
        public String projectInvestmentName;
    }

    //单位类型105：建设单位、106：设计单位、107：勘察单位、108：监理单位、111：安监单位、112：质检单位、110：施工单位
    public static class Unit {
        public String unitName;
        public String type;
        public String legalRepre; //法人代表
    }


    public static class UnitQualifications {
        public String grade;
        public String qualName;
    }

    //项目人类型 115:项目经理,116:项目负责人,117:项目参与人,118:项目技术负责人,
    // 119:总工程师,120:项目总监,121:监理工程师,180:劳务管理员,199:项目总工,122:质检员,123:技术负责人
    public static class Person {
        public String person;
        public String personType;
    }

    public Unit getUnitByType(String type) {
        for (Unit unit : unitList) {
            if (type.equals(unit.type)) {
                return unit;
            }
        }
        return new Unit();
    }

    public Person getPersonByType(String type) {
        for (Person person : unitPersonList) {
            if (type.equals(person.personType)) {
                return person;
            }
        }
        return new Person();
    }
}
