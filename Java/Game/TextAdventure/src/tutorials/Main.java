package tutorials;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        //게임에 관련된 변수들
        String[] enemies = {"스켈레톤","좀비","전사","암살자"};    //적의 이름들
        int maxEnemyHealth = 100;   //적의 full HP
        int enemyAttackDamage = 30; //적의 공격력

        //플레이어 변수
        int health = 120;           //캐릭터의 Hp
        int attackDamage = 35;      //캐릭터의 공격력
        int healthPotionsNum = 3;   //캐릭터의 회복물약 갯수
        int healAmount = 40;        //물약의 회복량
        int potionDropPercent = 20; //물약 드롭률
        int defensePercent = 10;    //방어율

        boolean running = true;     //던전의 입장 여부

        System.out.println("던전에 오신 것을 환영합니다!");


        GAME:
        while (running){
            System.out.println("--------------------------------------------------------------------------------------------");

            int enemyHealth = r.nextInt(maxEnemyHealth);    //적의 체력을 랜덤으로 지정
            String enemy = enemies[r.nextInt(enemies.length)];  //적을 랜덤으로 지정
            System.out.println("\t# " + enemy + "(이)가 나타났다! #\n");

            while(enemyHealth > 0){ //적의 체력이 0보다 클 때,
                System.out.println("\t나의 HP: " + health);
                System.out.println("\t적의 HP: " + enemyHealth);
                System.out.println("\n\t어떤 행동을 하시겠습니까?");
                System.out.println("\t1. 공격!");
                System.out.println("\t2. HP포션 마시기");
                System.out.println("\t3. 도망간다!");

                String input = sc.nextLine();       //명령을 입력 받음
                if(input.equals("1")){          //공격!
                    int damageDeal = r.nextInt(attackDamage);   //준 데미지 랜덤으로 측정
                    int damageTaken = r.nextInt(enemyAttackDamage); //받은 데미지 랜덤으로 측정

                    if(r.nextInt(100)<defensePercent){      //방어에 성공했을 때,
                        enemyHealth -= damageDeal;  //준 데미지를 적의 HP에서 깎고

                        System.out.println("\t> 당신은 "+enemy+"에게 "+damageDeal+"만큼의 데미지를 줬다.");
                        System.out.println("\t> 당신은 방어에 성공하셨습니다.");

                    }else{                          //방어에 실패했을 때,
                        enemyHealth -= damageDeal;  //준 데미지를 적의 HP에서 깎고
                        health -= damageTaken;      //받은 데미지를 내 HP에서 깎는다.

                        System.out.println("\t> 당신은 "+enemy+"에게 "+damageDeal+"만큼의 데미지를 줬다.");
                        System.out.println("\t> 당신은 반격으로 "+damageTaken+"만큼의 데미지를 받았습니다.");

                        if(health < 1){     //내 HP가 1보다 작으면 던전 탈출
                            System.out.println("\n\t> 당신은 너무 많은 피해를 받아 더이상 던전에 있을 수 없습니다.\n");
                            break;
                        }
                    }

                }else if(input.equals("2")){    //HP포션 마시기
                    if(healthPotionsNum > 0){   //포션이 남아있을 때,
                        health += healAmount;   //회복
                        healthPotionsNum--;     //포션 갯수 1줄임
                        System.out.println("\t> 힐링 포션을 마셨습니다, 당신은 " + healAmount +"만큼 HP를 회복하였습니다."
                        +"\n\t> 나의 남은 HP: " + health
                        +"\n\t> 남은 포션의 갯수: "+healthPotionsNum+"개\n");

                    }else{                      //포션이 없을 때,
                        System.out.println("\t> 물약이 남아있지 않습니다! 다른 행동을 선택해 주세요!!");
                    }

                }else if(input.equals("3")){    //도망간다!
                    System.out.println("\t당신은 "+ enemy+"(으)로부터 도망치는데 성공했습니다!");
                    continue GAME;              //루프문 재실행

                }else{                          //1,2,3이외에 다른 것을 입력할 시
                    System.out.println("\t잘못된 명령어 입니다.");
                }
            }//while문 종료(적의 체력이 0보다 클 때,)

            if(health<1){           //내 HP가 1보다 작을 때,
                System.out.println("당신은 던전 공략에 실패하셨습니다.");
                break;
            }

            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println(" # " + enemy + "(이)가 쓰려졌습니다! #");
            System.out.println(" # 나의 남은 HP: " + health+" #");
            if(r.nextInt(100) < potionDropPercent){ //포션을 얻을 확률
                healthPotionsNum++;
                System.out.println(" # " + enemy + "에게서 HP포션을 얻었다! # ");
                System.out.println(" # 남은 HP포션의 갯수: "+healthPotionsNum+"개. # ");

            }
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("어떤 행동을 선택하시겠습니까?");
            System.out.println("1. 전투를 계속 진행한다.");
            System.out.println("2. 던전을 나간다.");

            String input = sc.nextLine();

            while(!input.equals("1") && !input.equals("2")){    //1,2를 입력 안했을 경우
                System.out.println("잘못된 명령어 입니다!");
                input = sc.nextLine();
            }

            if(input.equals("1")){
                System.out.println("새로운 상대를 찾아나섭니다!");
            }else if(input.equals("2")){
                System.out.println("당신은 던전에서 나오셨습니다. 모험을 성공적으로 마쳤습니다!");
                break;
            }


        }
        System.out.println("############################");
        System.out.println("# 플레이 해주셔서 감사합니다!!! #");
        System.out.println("############################");

    }
}
