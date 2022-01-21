/*
 * Copyright 2003-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demo.app;
import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.EachProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import java.util.List;

@ConfigurationProperties("hogwarts")
public class Hogwarts {

    @NonNull
    @NotBlank
    private String headmaster;

    @Nullable
    private List<Student> students;

    @Nullable
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(@Nullable List<Student> students) {
        this.students = students;
    }

    @NonNull
    public String getHeadmaster() {
        return headmaster;
    }

    public void setHeadmaster(@NonNull String headmaster) {
        this.headmaster = headmaster;
    }

    @EachProperty("students")
    public static class Student {
        @NonNull
        @NotBlank
        private String firstName;

        @NonNull
        @NotBlank
        private String lastName;

        @NonNull
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(@NonNull String firstName) {
            this.firstName = firstName;
        }

        @NonNull
        public String getLastName() {
            return lastName;
        }

        public void setLastName(@NonNull String lastName) {
            this.lastName = lastName;
        }
    }

}
