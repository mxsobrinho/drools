/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.models.guided.dtable.shared.model;

import static org.drools.workbench.models.guided.dtable.shared.model.ActionSetFieldCol52.FIELD_BOUND_NAME;
import static org.drools.workbench.models.guided.dtable.shared.model.ActionSetFieldCol52.FIELD_FACT_FIELD;
import static org.drools.workbench.models.guided.dtable.shared.model.ActionSetFieldCol52.FIELD_TYPE;
import static org.drools.workbench.models.guided.dtable.shared.model.ActionSetFieldCol52.FIELD_UPDATE;
import static org.drools.workbench.models.guided.dtable.shared.model.ActionSetFieldCol52.FIELD_VALUE_LIST;
import static org.drools.workbench.models.guided.dtable.shared.model.ActionWorkItemSetFieldCol52.FIELD_PARAMETER_CLASSNAME;
import static org.drools.workbench.models.guided.dtable.shared.model.ActionWorkItemSetFieldCol52.FIELD_WORK_ITEM_NAME;
import static org.drools.workbench.models.guided.dtable.shared.model.ActionWorkItemSetFieldCol52.FIELD_WORK_ITEM_RESULT_PARAM_NAME;
import static org.drools.workbench.models.guided.dtable.shared.model.DTColumnConfig52.FIELD_DEFAULT_VALUE;
import static org.drools.workbench.models.guided.dtable.shared.model.DTColumnConfig52.FIELD_HEADER;
import static org.drools.workbench.models.guided.dtable.shared.model.DTColumnConfig52.FIELD_HIDE_COLUMN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ActionWorkItemSetFieldCol52Test extends ColumnTestBase {

    private ActionWorkItemSetFieldCol52 column1;
    private ActionWorkItemSetFieldCol52 column2;

    @Before
    public void setup() {
        column1 = new ActionWorkItemSetFieldCol52();
        column1.setWorkItemName("WID");
        column1.setWorkItemResultParameterName("param");
        column1.setParameterClassName("Type");
        column1.setBoundName("$var");
        column1.setFactField("field");
        column1.setType("Type");
        column1.setValueList("a,b,c");
        column1.setUpdate(false);
        column1.setHeader("header");
        column1.setHideColumn(false);
        column1.setDefaultValue(new DTCellValue52("default"));

        column2 = new ActionWorkItemSetFieldCol52();
        column2.setWorkItemName("WID");
        column2.setWorkItemResultParameterName("param");
        column2.setParameterClassName("Type");
        column2.setBoundName("$var");
        column2.setFactField("field");
        column2.setType("Type");
        column2.setValueList("a,b,c");
        column2.setUpdate(false);
        column2.setHeader("header");
        column2.setHideColumn(false);
        column2.setDefaultValue(new DTCellValue52("default"));
    }

    @Test
    public void testDiffEmpty() {
        checkDiffEmpty(column1, column2);
        assertEquals(column1, column2);
    }

    @Test
    public void testDiffWorkItemName() {
        column1.setWorkItemName("WID1");
        column2.setWorkItemName("WID2");

        checkSingleDiff(FIELD_WORK_ITEM_NAME, "WID1", "WID2", column1, column2);
        assertNotEquals(column1, column2);
    }

    @Test
    public void testDiffResultName() {
        column1.setWorkItemResultParameterName("param1");
        column2.setWorkItemResultParameterName("param2");

        checkSingleDiff(FIELD_WORK_ITEM_RESULT_PARAM_NAME, "param1", "param2", column1, column2);
        assertNotEquals(column1, column2);
    }

    @Test
    public void testDiffResultType() {
        column1.setParameterClassName("Type1");
        column2.setParameterClassName("Type2");

        checkSingleDiff(FIELD_PARAMETER_CLASSNAME, "Type1", "Type2", column1, column2);
        assertNotEquals(column1, column2);
    }

    @Test
    public void testDiffAll() {
        column1.setWorkItemName("WID1");
        column1.setWorkItemResultParameterName("param1");
        column1.setParameterClassName("Type1");
        column1.setBoundName("$var1");
        column1.setFactField("field1");
        column1.setType("Type1");
        column1.setValueList("a,b");
        column1.setUpdate(false);
        column1.setHeader("header1");
        column1.setHideColumn(false);
        column1.setDefaultValue(new DTCellValue52("default1"));

        column2.setWorkItemName("WID2");
        column2.setWorkItemResultParameterName("param2");
        column2.setParameterClassName("Type2");
        column2.setBoundName("$var2");
        column2.setFactField("field2");
        column2.setType("Type2");
        column2.setValueList("b,c");
        column2.setUpdate(true);
        column2.setHeader("header2");
        column2.setHideColumn(true);
        column2.setDefaultValue(new DTCellValue52("default2"));

        List<BaseColumnFieldDiff> diff = column1.diff(column2);
        assertNotNull(diff);
        assertEquals(11, diff.size());
        assertEquals(FIELD_HIDE_COLUMN, diff.get(0).getFieldName());
        assertEquals(false, diff.get(0).getOldValue());
        assertEquals(true, diff.get(0).getValue());
        assertEquals(FIELD_DEFAULT_VALUE, diff.get(1).getFieldName());
        assertEquals("default1", diff.get(1).getOldValue());
        assertEquals("default2", diff.get(1).getValue());
        assertEquals(FIELD_HEADER, diff.get(2).getFieldName());
        assertEquals("header1", diff.get(2).getOldValue());
        assertEquals("header2", diff.get(2).getValue());
        assertEquals(FIELD_BOUND_NAME, diff.get(3).getFieldName());
        assertEquals("$var1", diff.get(3).getOldValue());
        assertEquals("$var2", diff.get(3).getValue());
        assertEquals(FIELD_FACT_FIELD, diff.get(4).getFieldName());
        assertEquals("field1", diff.get(4).getOldValue());
        assertEquals("field2", diff.get(4).getValue());
        assertEquals(FIELD_TYPE, diff.get(5).getFieldName());
        assertEquals("Type1", diff.get(5).getOldValue());
        assertEquals("Type2", diff.get(5).getValue());
        assertEquals(FIELD_VALUE_LIST, diff.get(6).getFieldName());
        assertEquals("a,b", diff.get(6).getOldValue());
        assertEquals("b,c", diff.get(6).getValue());
        assertEquals(FIELD_UPDATE, diff.get(7).getFieldName());
        assertEquals(false, diff.get(7).getOldValue());
        assertEquals(true, diff.get(7).getValue());
        assertEquals(FIELD_WORK_ITEM_NAME, diff.get(8).getFieldName());
        assertEquals("WID1", diff.get(8).getOldValue());
        assertEquals("WID2", diff.get(8).getValue());
        assertEquals(FIELD_WORK_ITEM_RESULT_PARAM_NAME, diff.get(9).getFieldName());
        assertEquals("param1", diff.get(9).getOldValue());
        assertEquals("param2", diff.get(9).getValue());
        assertEquals(FIELD_PARAMETER_CLASSNAME, diff.get(10).getFieldName());
        assertEquals("Type1", diff.get(10).getOldValue());
        assertEquals("Type2", diff.get(10).getValue());
        assertNotEquals(column1, column2);
    }
}