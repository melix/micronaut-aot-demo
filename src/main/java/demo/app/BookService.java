/*
 * Copyright 2003-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demo.app;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Arrays;
import java.util.List;

@Controller
public class BookService {
    @Get("/books")
    public List<Book> getBooks() {
        return Arrays.asList(
                new Book("Dune", "Frank Herbert", 1965),
                new Book("Dune Messiah", "Frank Herbert", 1969),
                new Book("Children of Dune", "Frank Herbert", 1976),
                new Book("God Emperor of Dune", "Frank Herbert", 1981),
                new Book("Heretics of Dune", "Frank Herbert", 1984)
        );
    }
}
