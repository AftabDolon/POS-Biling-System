
package agora.ltd;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class ResultModel extends AbstractTableModel {
public void setResultSet(ResultSet results){
    try{
        ResultSetMetaData metadata=results.getMetaData();
        int columns=metadata.getColumnCount();
        columnNames=new String[columns];
        for(int i=0; i<columns; i++){
          columnNames[i]=metadata.getColumnLabel(i+1);
        }
        dataRows.clear();
        String[] rowData;
        while(results.next()){
            rowData=new String[columns];
            for(int i=0; i<columns; i++){
                rowData[i]=results.getString(i+1);
            }
            dataRows.addElement(rowData);
        }
        fireTableChanged(null);
    }
    catch(SQLException sqle){
        System.out.println(sqle);
    }
}
    @Override
    public int getRowCount() {
        return dataRows==null?0 : dataRows.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getValueAt(int row, int column) {
        return dataRows.elementAt(row)[column];
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column]==null? "No Name" : columnNames[column];
    }
    private String[] columnNames=new String[0];
    private Vector<String[]> dataRows=new Vector<String[]>();
}

