package org.platkmframework.database.query.common.vo;

import java.util.ArrayList;
import java.util.List;

/**
 *   Author:
*     Eduardo Iglesias
*   Contributors:
*   	Eduardo Iglesias - initial API and implementation
* @param <E> E
*/
public class CustomResultInfo<E> {

    /**
     * Atributo resultClass
     */
    private Class<E> resultClass;

    /**
     * Atributo columns
     */
    private List<String> columns;

    /**
     * Constructor CustomResultInfo
     * @param resultClass resultClass
     */
    public CustomResultInfo(Class<E> resultClass) {
        super();
        this.resultClass = resultClass;
        this.columns = new ArrayList<String>();
    }

    /**
     * getResultClass
     * @return Class
     */
    public Class<E> getResultClass() {
        return resultClass;
    }

    /**
     * addColumn
     * @param column column
     */
    public void addColumn(String column) {
        this.columns.add(column);
    }

    /**
     * getColumns
     * @return List
     */
    public List<String> getColumns() {
        return columns;
    }

    /**
     * addColumn
     * @param split split
     */
    public void addColumn(String[] split) {
        for (int i = 0; i < split.length; i++) {
            addColumn(split[i]);
        }
    }
}
