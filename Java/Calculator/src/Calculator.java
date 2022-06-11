import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {    //계산기 구현

    private JTextField inputSpace;          //JTextField로 화면 구현

    public Calculator(){

        inputSpace = new JTextField();      //빈공간에 JTextField를 생성하고 버튼으로 입력받음
        inputSpace.setEditable(false);      //편집 가능 여부는 불가능
        inputSpace.setBackground(Color.WHITE);  //배경색은 하얀색
        inputSpace.setHorizontalAlignment(JTextField.RIGHT);    //계산기에 숫자 입력시 오른쪽 정렬
        inputSpace.setFont(new Font("Arial",Font.BOLD,50)); //Arial체에 굵게 사이즈는 50
        inputSpace.setBounds(8,10,270,70);  //JTextField의 위치와 크기지정

        JPanel buttonPanel = new JPanel();                         //버튼패널생성
        buttonPanel.setLayout(new GridLayout(4,4,10,10)); //격자형태로(줄,칸 갯수,상하,좌우 간격)
        buttonPanel.setBounds(8,90,270,235);     //버튼 위치와 크기 지정

        String button_names[] = {"C","÷","×","=","7","8","9","+","4","5","6","-","1","2","3","0"};  //버튼에 들어갈 문자
        JButton buttons[] = new JButton[button_names.length];   //버튼 생성

        for(int i = 0; i < button_names.length; i++){
            buttons[i] = new JButton(button_names[i]);          //버튼마다 문자대입
            buttons[i].setFont(new Font("Arial",Font.BOLD,20)); //버튼의 글씨체 조정
            if(button_names[i] == "C"){
                buttons[i].setBackground(Color.RED);    //C버튼은 빨간색
            }else if((i>=4 && i<=6) || (i>=8 && i<=10) || (i>=12 && i<=14)){
                buttons[i].setBackground(Color.BLACK);  //숫자버튼은 검은색
            }else{
                buttons[i].setBackground(Color.GRAY);  //나머지 버튼은 회색
            }
            buttons[i].setForeground(Color.WHITE);      //글씨 색은 흰색
            buttons[i].setBorderPainted(false);         //버튼의 테두리 없앰
            buttonPanel.add(buttons[i]);                //버튼들을 버튼 패널에 추가

        }

        add(inputSpace);                    //inputSpace추가
        add(buttonPanel);                   //buttonPanel추가

       setLayout(null);                     //기본적으로 있는 레이아웃 관리자 제거
        setTitle("계산기");                  //타이틀을 계산기로 해서 생성
        setVisible(true);                   //창을 보이게 함(기본값은 안보임)
        setSize(300, 370);     //창 크기 지정
        setLocationRelativeTo(null);       //null입력시 창을 가운데에 띄움
                                           //다른 값을 입력시 그 값을 기준으로 창을 띄움
        setResizable(false);                //프레임의 크기를 사용자가 변경 불가능하게 처리
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프레임을 닫았을 때 메모리에서 삭제

    }
    public static void main(String[] args) {
        new Calculator();

    }
}
