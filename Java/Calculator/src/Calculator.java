import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calculator extends JFrame {    //계산기 구현

    private JTextField inputSpace;          //JTextField로 화면 구현
    private String num = "";                //계산식의 숫자를 담을 변수
    private ArrayList<String> equation = new ArrayList<>(); //숫자와 연산기호를 하나씩 구분해서 ArrayList에 담아줌

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
            buttons[i].addActionListener(new PadActionListener());  //Action Listener추가
            buttonPanel.add(buttons[i]);                //버튼들을 버튼 패널에 추가
        }

        add(inputSpace);                    //프레임에 inputSpace추가
        add(buttonPanel);                   //프레임에 buttonPanel추가

        setLayout(null);                    //기본적으로 있는 레이아웃 관리자 제거
        setTitle("계산기");                  //타이틀을 계산기로 해서 생성
        setVisible(true);                   //창을 보이게 함(기본값은 안보임)
        setSize(300, 370);     //창 크기 지정
        setLocationRelativeTo(null);       //null입력시 창을 가운데에 띄움
                                           //다른 값을 입력시 그 값을 기준으로 창을 띄움
        setResizable(false);                //프레임의 크기를 사용자가 변경 불가능하게 처리
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프레임을 닫았을 때 메모리에서 삭제

    }

    class PadActionListener implements ActionListener{  //버튼이 눌렀을때 실행하기 위해 사용
        @Override
        public void actionPerformed(ActionEvent e) {
            String operation = e.getActionCommand();    //어떤 버튼이 눌렸는지 받아옴
            if(operation.equals("C")){  //"C"를 눌렸을 시, 텍스트 초기화
                inputSpace.setText("");
            }else if(operation.equals("=")){    //"="를 눌렸을 시, 계산결과가 나오도록 한다.
                String result = Double.toString(calculate(inputSpace.getText()));
                //inputSpace의 text를 가지고 와서 calculate메서드 안에 넣어서 계산을 한다.
                inputSpace.setText("" + result);
                num = "";
            }else{                      //나머지는 그대로 계산기에 적어준다.
                inputSpace.setText(inputSpace.getText() + e.getActionCommand());
            }
        }
    }

    private void fullTextParsing(String inputText){
        equation.clear();

        for(int i = 0;i<inputText.length();i++){        //계산식 글자를 하나하나 거쳐가게 만듬
            char ch = inputText.charAt(i);

            if(ch == '-' || ch == '+' || ch == '×' || ch == '÷'){
                equation.add(num);  //연산기호를 거치게 된다면 앞은 숫자이므로 숫자먼저 추가
                num = "";           //num초기화 후 연산기호를 추가
                equation.add(ch + "");
            }else{                  //숫자인 경우 num문자에 더해준다.
                num = num + ch;
            }
        }
        equation.add(num);          //반복문이 끝나고 최종적으로 있는 num도 ArrayList에 추가
    }

    public double calculate(String inputText){
        fullTextParsing(inputText); //실행하면 ArrayList에 숫자와 연산기호들이 담기게 됨

        double prev = 0;
        double current = 0;
        String mode = "";

        for(String s : equation){
            if(s.equals("+")){
                mode = "add";
            }else if(s.equals("-")){
                mode = "sub";
            }else if(s.equals("×")){
                mode = "mul";
            }else if(s.equals("÷")){
                mode = "div";
            }else{  //나머지 숫자의 경우 문자열을 형변환 시켜줘야됨
                current = Double.parseDouble(s);
                if(mode.equals("add")){
                    prev += current;
                }else if(mode.equals("sub")){
                    prev -= current;
                }else if(mode.equals("mul")){
                    prev *= current;
                }else if(mode.equals("div")){
                    prev /= current;
                }else{
                    prev = current;
                }
            }
        }
        return prev;
    }

    public static void main(String[] args) {
        new Calculator();

    }
}
