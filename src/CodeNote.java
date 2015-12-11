/**
 * Created by ��ƽ on 2015/9/23.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.event.*;
import java.util.*;

public class CodeNote extends JFrame{
    Vector<String> s=new Vector<String>(Arrays.asList(
            "choice0","choice1","choice2","choice3","choice4",
            "choice5","choice6","choice7","choice8","choice9",
            "choice10","choice11","choice12","choice13","choice14"
    ));
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();//���,װ��ť
    JTextArea textEditor =new JTextArea();;//�ı���
    JList listCode = new JList(s);//�б�

    JScrollPane JSP=new JScrollPane(listCode,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    JButton btnNew;
    JButton btnSave;
    JButton btnDelete;
    JButton btnCheat;

    CodeNote(){
        super("CodeNote");
        Container con=getContentPane();

        setBounds(225, 60, 900, 600);//���ô����ʼ��λ�ü���С
        con.setLayout(null);//����Բ���
        con.setBackground(Color.gray);//������ɫ

        //List�¼�
        listCode.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                textEditor.setText("you selected:" + listCode.getSelectedValue());
            }
        });

        FlowLayout flow=new FlowLayout();

        btnNew=new JButton("�½�");
        btnSave=new JButton("����");
        btnDelete=new JButton("ɾ��");
        btnCheat=new JButton("����");

        btnNew.setFocusPainted(false);
        btnSave.setFocusPainted(false);
        btnDelete.setFocusPainted(false);
        btnCheat.setFocusPainted(false);

        btnNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String str = JOptionPane.showInputDialog(null, "Enter some text : ", "title", 1);
                s.add(str);
                listCode.updateUI();
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String item = listCode.getSelectedValue().toString();
                    s.remove(item);
                    listCode.updateUI();
                }
                catch(Exception ee){
                    //ee.printStackTrace();
                }
            }
        });
        p1.setLayout(flow);
        p2.setLayout(null);
        p1.setBounds(0, 1, 100, 139);
        p2.setBounds(0, 141, 100, 439);
        listCode.setBounds(0, 0, 90, 439);
        JSP.setBounds(90,0,8,439);
        textEditor.setBounds(101, 1, 798, 599);

        listCode.setVisibleRowCount(28);//�趨�б���Ŀɼ�����

        btnNew.setPreferredSize(new Dimension(100, 30));
        btnSave.setPreferredSize(new Dimension(100, 30));
        btnDelete.setPreferredSize(new Dimension(100, 30));
        btnCheat.setPreferredSize(new Dimension(100, 30));

        con.add(p1);
        con.add(p2);

        p1.add(btnNew);
        p1.add(btnSave);
        p1.add(btnDelete);
        p1.add(btnCheat);

        p2.add(listCode);
        p2.add(JSP);

        add(textEditor);

        setVisible(true);//���ô���Ϊ�ɼ�
        this.setResizable(false);//���ô���ȡ����󻯼����죨�̶�ԭ�ȴ����С��
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�ر�JAVA�����



    }

    public static void main(String[] args) {

        CodeNote one = new CodeNote();

    }

}
