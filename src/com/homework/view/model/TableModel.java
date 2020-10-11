package com.homework.view.model;

import com.homework.po.Question;
import com.homework.service.QusetionService;
import com.homework.service.impl.QuestionServiceImpl;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * ClassName:    TableModel
 * Package:    com.homework.view
 * Description:
 * Datetime:    2020/9/24   19:25
 * Author:   ${小情绪}
 */
public class TableModel  extends AbstractTableModel {
    private QusetionService questionService=new QuestionServiceImpl();
    private  String[] columnNames={"序号","问题"};
    private  Object[][] data=null;

    //传入问题集合
    public TableModel(List<Question> questionsList) {
        data=new Object[questionsList.size()][columnNames.length];
        if(null!=questionsList&&questionsList.size()>0){
            for (int i=0;i<questionsList.size();i++){
                Question question=questionsList.get(i);
//                data[i][0]=question.getId();
                data[i][0]=i;
                if(question.getTitle().length()<8){
                    data[i][1]=question.getTitle();
                }else{
                    data[i][1]=question.getTitle().substring(0,7)+"...";
                }

            }
        }
    }


    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
