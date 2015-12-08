/*
 * Copyright (c) 2015 FUJI Goro (gfx).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.gfx.android.orma.processor.test;

import com.google.testing.compile.JavaFileObjects;

import com.github.gfx.android.orma.processor.OrmaProcessor;

import org.junit.Test;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class SchemaValidatorTest {

    @Test
    public void testDuplicateColumnNames() throws Exception {
        JavaFileObject classModelWithDuplicateColumnNames = JavaFileObjects
                .forSourceString("ModelWithDuplicateColumnNames",
                        "import com.github.gfx.android.orma.annotation.*;\n"
                                + "@Table\n"
                                + "public class ModelWithDuplicateColumnNames {\n"
                                + "@Column(\"foo\") String foo;\n"
                                + "@Column(\"foo\") String bar;\n"
                                + "}\n");

        assert_().about(javaSource())
                .that(classModelWithDuplicateColumnNames)
                .processedWith(new OrmaProcessor())
                .failsToCompile()
                .withErrorCount(2)
                .withErrorContaining("Duplicate column names \"foo\" found");
    }

    @Test
    public void testDuplicateColumnNamesCaseInsensitive() throws Exception {
        JavaFileObject classModelWithDuplicateColumnNames = JavaFileObjects
                .forSourceString("ModelWithDuplicateColumnNames",
                        "import com.github.gfx.android.orma.annotation.*;\n"
                                + "@Table\n"
                                + "public class ModelWithDuplicateColumnNames {\n"
                                + "@Column String foo;\n"
                                + "@Column String FOO;\n"
                                + "}\n");

        assert_().about(javaSource())
                .that(classModelWithDuplicateColumnNames)
                .processedWith(new OrmaProcessor())
                .failsToCompile()
                .withErrorCount(2)
                .withErrorContaining("Duplicate column names \"foo\" found");
    }

    @Test
    public void testDuplicateColumnNamesIncludingPrimaryKey() throws Exception {
        JavaFileObject classModelWithDuplicateColumnNames = JavaFileObjects
                .forSourceString("ModelWithDuplicateColumnNames",
                        "import com.github.gfx.android.orma.annotation.*;\n"
                                + "@Table\n"
                                + "public class ModelWithDuplicateColumnNames {\n"
                                + "@PrimaryKey String foo;\n"
                                + "@Column(\"foo\") String bar;\n"
                                + "}\n");

        assert_().about(javaSource())
                .that(classModelWithDuplicateColumnNames)
                .processedWith(new OrmaProcessor())
                .failsToCompile()
                .withErrorCount(2)
                .withErrorContaining("Duplicate column names \"foo\" found");
    }

    @Test
    public void testNoColumnInTable() throws Exception {
        JavaFileObject classModelWithDuplicateColumnNames = JavaFileObjects
                .forSourceString("NoColumnInTable",
                        "import com.github.gfx.android.orma.annotation.*;\n"
                                + "@Table\n"
                                + "public class NoColumnInTable {\n"
                                + "String foo;\n"
                                + "String bar;\n"
                                + "}\n");

        assert_().about(javaSource())
                .that(classModelWithDuplicateColumnNames)
                .processedWith(new OrmaProcessor())
                .failsToCompile()
                .withErrorCount(1)
                .withErrorContaining("No @Column nor @PrimaryKey is defined in NoColumnInTable");
    }

}