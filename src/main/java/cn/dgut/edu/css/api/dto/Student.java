package cn.dgut.edu.css.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZHBlue
 * @todo
 * @copyright： ZHBlue
 * @date：2021/6/10
 */
@NoArgsConstructor
@Data
public class Student {

    @JsonProperty("number")
    private String number;
    @JsonProperty("name")
    private String name;
    @JsonProperty("grade")
    private String grade;
    @JsonProperty("major")
    private String major;
    @JsonProperty("class")
    private String classX;
    @JsonProperty("major_id")
    private Integer majorId;
    @JsonProperty("major_class_id")
    private Integer majorClassId;
    @JsonProperty("sex")
    private String sex;
    @JsonProperty("classes")
    private ClassesDTO classes;
    @JsonProperty("majors")
    private MajorsDTO majors;

    @NoArgsConstructor
    @Data
    public static class ClassesDTO {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("major_id")
        private Integer majorId;
        @JsonProperty("grade")
        private Integer grade;
        @JsonProperty("class_code")
        private String classCode;
        @JsonProperty("class_name")
        private String className;
        @JsonProperty("name_code")
        private String nameCode;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("college_id")
        private Integer collegeId;
        @JsonProperty("deleted_at")
        private Object deletedAt;
    }

    @NoArgsConstructor
    @Data
    public static class MajorsDTO {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("college_code")
        private String collegeCode;
        @JsonProperty("code")
        private String code;
        @JsonProperty("name")
        private String name;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("college_id")
        private Integer collegeId;
        @JsonProperty("name_code")
        private String nameCode;
        @JsonProperty("deleted_at")
        private Object deletedAt;
        @JsonProperty("college")
        private CollegeDTO college;

        @NoArgsConstructor
        @Data
        public static class CollegeDTO {
            @JsonProperty("id")
            private Integer id;
            @JsonProperty("code")
            private String code;
            @JsonProperty("name")
            private String name;
            @JsonProperty("created_at")
            private String createdAt;
            @JsonProperty("updated_at")
            private String updatedAt;
            @JsonProperty("deleted_at")
            private Object deletedAt;
        }
    }
}
