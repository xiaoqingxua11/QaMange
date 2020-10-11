package com.homework.view.model;

import com.homework.po.Question;
import com.homework.po.Reply;
import com.homework.service.QusetionService;
import com.homework.service.impl.QuestionServiceImpl;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * ClassName:    DetaiModel
 * Package:    com.homework.view.model
 * Description:
 * Datetime:    2020/9/26   17:06
 * Author:   ${小情绪}
 */
public class DetaiModel  extends AbstractTableModel {
    private QusetionService questionService=new QuestionServiceImpl();
    private  String[] columnNames={"回复","点赞次数"};
    private  Object[][] data=null;

    //传入问题集合
    public DetaiModel(List<Reply> replyList) {
        data=new Object[replyList.size()][columnNames.length];
        if(null!=replyList&&replyList.size()>0){
            for (int i=0;i<replyList.size();i++){
                Reply reply=replyList.get(i);
                data[i][0]=reply.getContent();
                data[i][1]=reply.getGoodcount();
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
