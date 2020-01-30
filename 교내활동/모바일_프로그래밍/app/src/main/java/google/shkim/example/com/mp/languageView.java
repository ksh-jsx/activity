package google.shkim.example.com.mp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class languageView extends Activity {

    ArrayList arrayList;
    private Database dbHelper;
    String selectedItem;
    private static final String DEBUG_TAG = "{LOG_ANDROID}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_view);
        dbHelper = new Database(getApplicationContext(), "travelHelper.db", null, 1);
        Cursor cursor1 = dbHelper.select("SELECT * FROM spinnerSelect");
        cursor1.moveToFirst();
        Log.d(DEBUG_TAG, "list : " + cursor1.getString(0));

        String[][] itemsA_1 = {
                {"안녕하세요\n\nhello\n\n헬로우", "안녕히 가세요\n\nGood bye\n\n굿 바이", "처음 뵙겠습니다\n\nnice to meet you\n\n나이스 투 미트 유", "제 이름은 ***입니다\n\nMy name is ***\n\n마이 네임 이즈 ***"},
                {"안녕하세요\n\nこんにちは\n\n\n곤니찌와", "안녕히 가세요\n\nさようなら\n\n사요나라", "처음 뵙겠습니다\n\n始めまして\n\n하지메마시떼", "저는 ***입니다\n\n私は***です\n\n와따시와***데스"},
                {"안녕하세요\n\n你好\n\n니하오", "안녕히 가세요\n\n再见\n\n짜이찌엔", "처음 뵙겠습니다\n\n初次见面\n\n하추츠 젠 멘", "저는 ***입니다\n\n我叫***\n\n워 쟈오 ***"},
                {"안녕하세요\n\nBonjours\n\n봉쥬르", "안녕히 가세요\n\nAu revoir\n\n오흐브와흐", "처음 뵙겠습니다\n\nEnchantée\n\n엉셩떼", "저는 ***입니다\n\nJe suis ***\n\n즈 쒸 ***"},
                {"안녕하세요\n\nHola\n\n올라", "안녕히 가세요\n\nAdiós\n\n아디오스", "처음 뵙겠습니다\n\nMucho gusto\n\n무쵸 구스토", "저는 ***입니다\n\nsoy ***\n\n소이 ***"}
        };
        String[][] itemsA_2 = {

                {"부탁합니다\n\nplease\n\n플리즈\n", "도와주세요\n\nhelp me\n\n헬프 미\n"},
                {"부탁합니다\n\nお願いします\n\n오네가이시마스\n", "도와주세요\n\n手伝ってくださいよ\n\n데츠닷테 쿠사다이요\n"},
                {"부탁합니다\n\n拜托了\n\n빠이 트워 러", "도와주세요\n\n请帮帮我\n\n싱빵빵워"},
                {"부탁합니다\n\ns'il vous plaît\n\n씰 부 쁠레", "도와주세요\n\nAidez-moi\n\n아이데즈 므와"},
                {"부탁합니다\n\nPor favor\n\n뽀르 파보르", "도와주세요\n\n¡Ayúdenme!\n\n"}
        };
        String[][] itemsA_3 = {
                {"감사합니다\n\nThanks \n\n땡쓰", "축하합니다\n\nCongratulations\n\n콩그레츄레이션스","대단해요\n\nGreat\n\n그레잇"},
                {"감사합니다\n\nありがとうございます\n\n아리가또고자이마스","축하합니다\n\nおめでとうございます\n\n오메데토고자이마스","대단해요\n\nご立派です\n\n고릿파데스"},
                {"감사합니다\n\n谢谢\n\n셰셰","축하합니다\n\n恭喜\n\n공씨","대단해요\n\n了不起\n\n리아오 부찌"},
                {"감사합니다\n\nMerci \n\n멜흐씨","축하합니다\n\nFélicitations.\n\n펠리씨따씨움","대단해요\n\nC'est incroyable.\n\n쎄 땅끄화이아블르"},
                {"감사합니다\n\nGracias\n\n그라씨아스","축하합니다\n\n¡Felicidades!\n\n펠리씨다데스","대단해요\n\n¡Es genial!\n\n에스 헤니알"}
        };
        String[][] itemsA_4 = {
                {"죄송합니다\n\nI'm sorry\n\n아임 쏘리","잘못했습니다\n\nI was wrong\n\n아이 워즈 롱","괜찮습니다\n\nThat's okey\n\n댓츠 오케이"},
                {"죄송합니다\n\nすいません\n\n스미마셍","잘못했습니다\n\n申し訳ありませんでした\n\n모시와케 아리마센데시타","괜찮습니다\n\n大丈夫です\n\n다이죠부데스"},
                {"죄송합니다\n\n不好意思\n\n부 하오 이쓰","잘못했습니다\n\n我做错了\n\n워 주워 쿠워 라","괜찮습니다\n\n没关系\n\n메이 퀀시"},
                {"죄송합니다\n\nExcuse-moi\n\n엑스큐즈 므와","잘못했습니다\n\nje m'excuse\n\n즈 멕스뀌즈","괜찮습니다\n\nÇa ne fait rien\n\n싸 느 페 히앙"},
                {"죄송합니다\n\nLo siento\n\n로 씨엔토","잘못했습니다\n\nLo siento\n\n로 씨엔토","괜찮습니다\n\nNo pasa nada\n\n노 빠사 나다"}
        };
        String[][] itemsA_5 = {
                {"\n질문있어요\n\nI have a question\n\n아이 헤브 어 퀘스쳔\n","\n네/아니요\n\nyes/ no\n\n예스/노\n", "\n***은 어디에 있나요?\n\nwhere is ***\n\n웨얼 이즈 ***", "\n이건 뭐예요?\n\nwhat is it?\n\n왓 이즈 잇\n"},
                {"\n질문있어요\n\n質問があります\n\n시츠몬가아리마스\n", "\n네/아니요\n\nはい/ いいえ\n\n하이/이이에\n", "\n***은 어디에 있나요?\n\n***はどこにあります\n\n***와 도코니 아리마스\n", "\n이건 뭐예요?\n\nこれは何ですか\n\n꼬레와 난데스까\n"},
                {"\n질문있어요\n\n我有问题\n\n워료 뭰 티\n", "\n네/아니요\n\n是/ 不是\n\n시/부시\n", "\n***은 어디에 있나요?\n\n***在哪里呢\n\n***쎼 나리나\n", "\n이건 뭐예요?\n\n这是什么?\n\n저 쓰 션머?\n"},
                {"\n질문있어요\n\nJ'ai une question\n\n제 윈 퀘스티옹\n", "\n네/아니요\n\nOui/ non\n\n위/논\n", "\n***은 어디에 있나요?\n\nOù est le ***\n\n워 레 ***\n", "\n이건 뭐예요?\n\nQu'est-ce que c'est?\n\n께스크 쎄?\n"},
                {"\n질문있어요\n\nTengo preguntas\n\n탠고 프레곤타스\n", "\n네/아니요\n\nSí/ no\n\n씨/노\n", "\n***은 어디에 있나요?\n\n¿Dónde está la ***\n\n둔데 에스타 라 ***\n", "\n이건 뭐예요?\n\n¿Qué es esto?\n\n꿰 에스토?\n"}
        };

        String[][] itemsB_1 = {
                {"\n주문할게요\n\nI'd like to order\n\n아이드 라이크 투 오덜\n","\n조금 있다가 주문할게요\n\nI'll order after a while\n\n아 윌 오덜 애프털 어 와일\n", "\n이것과 저것은 무엇이 다르죠?\n\nHow are this and that different?\n\n하우 얼 디스 앤 댓 디프런트?\n","\n물 좀 주세요\n\nPlease give me some water\n\n플리즈 기브 미 썸 워터\n", "\n이걸로 주세요\n\nthis one please\n\n디스 원 플리즈\n", "\n이건 뭔가요?\n\nWhat is it\n\n왓 이즈 잇?\n "},
                {"\n주문할게요\n\n注文します\n\n주문시마스\n","\n조금 있다가 주문할게요\n\n少ししてから注文します\n\n스코시시테카라 주문시마스\n", "\n이것과 저것은 무엇이 다르죠?\n\nこれとあれは、何が違うんですか\n\n코레토 아레와 나니가 치가운데스카\n","\n물 주세요\n\nお水ください\n\n오미즈 구다사이\n", "\n이걸로 주세요\n\nこれでください\n\n꼬레데쿠다사이\n","\n이건 뭔가요?\n\nこれは何ですか\n\n꼬레와 난데스까\n"},
                {"\n주문할게요\n\n我要点菜\n\n워오 야오 덴 차이\n","\n조금 있다가 주문할게요\n\n等一下再点菜\n\n덩 이 시야짜이 덴 차이\n", "\n이것과 저것은 무엇이 다르죠?\n\n这个和那个有什么不同\n\n저거 허 나거 여우 선머 뿌퉁?\n","\n물 좀 주세요\n\n请给我水\n\n칭 덴 워 수이\n", "\n이걸로 주세요\n\n就给我这个吧\n줘 게이 워 저거바\n\n","\n이건 뭔가요\n\n这是什么呢\n\n줘셰 셔만나\n"},
                {"\n주문할게요\n\nJe vous en prie\n\n즈 부스 언 피\n","\n조금 있다가 주문할게요\n\nJe vais commander un petit peu\n\n즈 베 꼬멍데 앙 쁘 쁠뤼 따흐\n", "\n이것과 저것은 무엇이 다르죠?\n\nQuelle est la différence entre ça et ça?\n\n껠 에 라 디페헝쓰 엉트로 싸 에 싸?\n","\n물 좀 주세요\n\nDe l'eau, s'il vous plait\n\n드 로, 씰 부 쁠레\n", "\n이걸로 주세요\n\nDonnez-moi ceci\n\n두니 므어 쓰시\n"},
                {"\n주문할게요\n\nPuedo pedirte algo\n\n푸도 페디르테 아르고\n","\n조금 있다가 주문할게요\n\nVamos a pedir más tardev\n\n바모스 아 뻬디르 마스 따르데\n", "\n이것과 저것은 무엇이 다르죠?\n\n¿Qué diferencia hay entre esto y aquello?\n\n께 디페엔씨아 아이 엔뜨레 에스또 이 아께요?\n","\n물 좀 주세요\n\nDeme agua\n\n데메 아구아\n", "\n이걸로 주세요\n\nDéme esto\n\n데메 에스토\n"}
        };
        String[][] itemsB_2 = {
                {"\n예약했습니다\n\nI made a reservation\n\n아이 메이드 오 리솔베이션\n","\n창가 자리로 부탁합니다\n\nI'd like a window seat, please\n\n아이드라익어윈도우싯,플리즈\n", "\n저쪽 테이블로 옮기고 싶습니다\n\nI want to move to the table over there\n\n아이 완트 투 무브 투 더 테이블 오버 데어\n"},
                {"\n예약했습니다\n\n予約しました\n\n요야쿠시마시타\n", "\n창가 자리로 부탁합니다\n\n窓側の席、お願いします\n\n마도가와세키,오네가이시마스\n", "\n저쪽 테이블로 옮기고 싶습니다\n\nあっちのテーブルに移りたいんですが\n\n앗치노 테-부루니 우츠리타인데스까\n"},
                {"\n예약했습니다\n\n预约了\n\n유유엘 라\n", "\n창가 자리로 부탁합니다\n\n请给我靠窗的位子\n\n칭 게이 워 카오 촹 더 웨이쯔\n", "\n저쪽 테이블로 옮기고 싶습니다\n\n我们想换到那一桌\n\n워먼 샹 환타오 나 이 줘\n"},
                {"\n예약했습니다\n\nJ'ai fait une réservation\n\n제 피나 헤사레스트옹\n", "\n창가 자리로 부탁합니다\n\nPrès de la fenêtre, s'il vous plaît\n\n프레 드 라 프네트르, 씰 부 쁠레\n", "\n저쪽 테이블로 옮기고 싶습니다\n\nJe voudrais aller à l'autre table\n\n즈 부드헤 알레 아 로토르 따블르\n"},
                {"\n예약했습니다\n\nHice una reservación\n\n이체 우나 레세르바시온\n", "\n창가 자리로 부탁합니다\n\nEn la ventana, por favor\n\n엔 라 벤따나, 뽀르 파보르\n", "\n저쪽 테이블로 옮기고 싶습니다\n\nQuisiera cambiarme a la mesa de ahí\n\n끼시에라 깜비아르메 알 라 메사 데 아이\n"}
        };
        String[][] itemsB_3 = {
                {"\n계산할게요\n\nI'll pay for the bill\n\n아윌 페이 포 더 빌\n", "\n따로 계산할게요\n\nI'll pay it separately\n\n아 윌 페이 잇 세퍼럿리\n", "\n남은음식 포장해주세요\n\nPlease pack the leftovers\n\n플리즈 팩 더 레프트오버스\n"},
                {"\n계산할게요\n\nお勘定お願いします\n\n오칸조오 오네가이시마스\n", "\n따로 계산할게요\n\n別々に計算します\n\n베츠베츠니 케에산시마스\n","\n남은 음식 포장해주세요\n\n残った食べ物包装してください\n\n노콧타 타베모노호오소오 시테쿠다사이\n"},
                {"\n계산할게요\n\n我要买单\n\n워 야오 마이 딴\n", "\n따로 계산할게요\n\n分开付\n\n떤 까이 후\n","\n남은 음식 포장해주세요\n\n剩菜请帮我打包\n\n슈잉 타히 췽 펑 워 다붜\n"},
                {"\n계산할게요\n\nJe vais payer\n\n즈 베 뻬이에\n", "\n따로 계산할게요\n\nChacun va payer\n\n쌰낑 빠 뻬이에\n","\n남은 음식 포장해주세요\n\nEmballez-moi ce plat\n\n엄발리 므어 세 플라\n"},
                {"\n계산할게요\n\nVoy a pagar la cuenta\n\n보이 아 빠가르 라 꾸엔따\n", "\n따로 계산할게요\n\n¿Nos cobra por separado, por favor?\n\n노스 꼬브라 뽀르 세빠라도, 뽀르 파보르?\n","\n남은 음식 포장해주세요\n\nHae me los platos restantes\n\n아에 에메 로쓰 쁠라또스 레스딴뗴스\n"}
        };
        String[][] itemsB_4 = {
                {"\n맛이 이상해요\n\nIt tastes weird\n\n잇 테이스츠 위얼드\n", "\n다른 음식으로 바꿔주세요\n\nPlease change to another dish\n\n플리즈 체인지 투 어나더 뒤시\n", "\n환불해주세요\n\nPlease give me a refund\n\n플리즈 깁 미 어 리펀드\n"},
                {"\n맛이 이상해요\n\n味が変です\n\n아지가 헨데스\n", "\n다른 음식으로 바꿔주세요\n\n他の料理に変えてください\n\n호카노료 오리니카에테 쿠다사이\n", "\n환불해주세요\n\n払い戻してください\n\n하라이모도시테 쿠다사이\n"},
                {"\n맛이 이상해요\n\n味道奇怪\n위따오 쉬 꾸에\n\n", "\n다른 음식으로 바꿔주세요\n\n请帮我换成其他的食物\n\n칭 빵 궈 한췅 취 하데쉬우\n", "\n환불해주세요\n\n请给我退款\n\n칭 귀훠 퀴 환\n"},
                {"\n맛이 이상해요\n\nCe plat est bizarre.\n\n씌 쁠레 에 비자르\n", "\n다른 음식으로 바꿔주세요\n\nJe veux une autre nourriture.\n\n쥬 브이 노흐틀 노릿쥬\n", "\n환불해주세요\n\nVeuillez me rembourser\n\n뷔긱 미 험복씨\n"},
                {"\n맛이 이상해요\n\nSabe raro\n\n싸베 르라로\n", "\n다른 음식으로 바꿔주세요\n\nCámbieme lo a otro plato\n\n깜비에미 로 아 오트로 쁠라또\n", "\n환불해주세요\n\nQuiero un reembolso\n\n뀌에르 운 뤼엠볼소\n"}
        };

        String[][] itemsC_1 = {
                {"\n짐은 몇 개까지 부칠 수 있나요?\n\nHow many bags can i check?\n\n하우 매니 백스 캔 아이 체크\n",
                        "\n비상구 근처 자리에 앉을 수 있을까요?\n\nCan i have a seat near the emergency exit?\n\n캔 아이 해브 어 싯 니어 더 이머전시 엑짓\n",
                        "\n깨지기 쉬운 물건이니 가방에 표시해 주세요.\n\nPlease mark this bag as 'fragile'.\n\n플리즈 마크 디스 백 에즈 '프레질'\n",
                        "\nC2 게이트로 가려면 어디로 가야 하나요?\n\nHow can i get to gate C2?\n\n하우 캔 아이 갯 투 게이트 씨투\n"},
                {"\n짐은 몇 개까지 부칠 수 있나요?\n\n荷物はいくつまで送ることができますか？\n\n니모츠와 이쿠츠 마데 오쿠루 코토가 데키마스카?\n",
                        "\n비상구 근처 자리에 앉을 수 있을까요?\n\n非常口近くの席に座ることができますか？\n\n히죠구치 치카라노 세키니 스와루 코토가 데키마스카?\n",
                        "\n깨지기 쉬운 물건이니 가방에 표시해 주세요.\n\n割れ物だからバッグに表示してください。\n\n와레모노다카라 바그니 효지 시테 쿠다사이\n",
                        "\nC2 게이트로 가려면 어디로 가야 하나요?\n\nどうやってC2ゲートに行くことができますか？\n\n도 얏떼 C2 게토니 이쿠 코토가 데키마스카?\n"},
                {"\n짐은 몇 개까지 부칠 수 있나요?\n\n我可以携带多少包？\n\n워 커이 지에따이 뚜 샤오 빠오?\n",
                        "\n비상구 근처 자리에 앉을 수 있을까요?\n\n我可以坐在紧急出口附近吗？\n\n워 커이 쭈어 짜이 찐찌 츄커우 푸찐 마?\n",
                        "\n깨지기 쉬운 물건이니 가방에 표시해 주세요.\n\n请在袋子上做标记，因为它很脆弱。\n\n칭짜이 따이찌 샹 쭈 삐야오찌 이웨이 타 흔 츠이뤄\n",
                        "\nC2 게이트로 가려면 어디로 가야 하나요?\n\n我在哪里去C2门？\n\n워 짜이 나니 취 C2 맨\n"},
                {"\n짐은 몇 개까지 부칠 수 있나요?\n\nCombien de sacs puis-je porter?\n\n콩비안 드 섹 부비부 바크티?\n",
                        "\n비상구 근처 자리에 앉을 수 있을까요?\n\nPuis-je m'asseoir près de la sortie d'urgence?\n\n푸이지 매쑤아 프레 데 라 소티 듀어정스?\n",
                        "\n깨지기 쉬운 물건이니 가방에 표시해 주세요.\n\nS'il vous plaît marquer sur le sac parce qu'il est fragile.\n\n씨 부 플레 마키 술 레 새크 파씨 킬 레 프라질\n",
                        "\nC2 게이트로 가려면 어디로 가야 하나요?\n\nOù vais-je à la porte C2?\n\n우 붸제 엘레 보크티 C2?\n"},
                {"\n짐은 몇 개까지 부칠 수 있나요?\n\n¿Cuántas bolsas puedo llevar?\n\n콴타스 볼사스 푸에도 뎨바?\n",
                        "\n비상구 근처 자리에 앉을 수 있을까요?\n\n¿Puedo sentarme cerca de la salida de emergencia?\n\n뿌에도 싼따르메 쎄르카 델 라 살리다 데 에메르쎈띠아\n",
                        "\n깨지기 쉬운 물건이니 가방에 표시해 주세요.\n\nPor favor marque en la bolsa porque es frágil.\n\n포르 파보르 마르케 엔 라 볼사 포르케 에스 프라킬\n",
                        "\nC2 게이트로 가려면 어디로 가야 하나요?\n\n¿A dónde voy a la puerta C2?\n\n아 돈데 보이 야 라 푸에르타 C2\n"}
        };
        String[][] itemsC_2 = {
                {"\n여기는 제 자리입니다.\n\nThis is my seat\n\n디스 이즈 마이 싯\n",
                        "\n짐 올리는 것 좀 도와주세요.\n\nCan you help me with my baggage?\n\n캔 유 헬프 미 윗 마이 배기지?\n",
                        "\n혹시 담요 한 장만 더 줄 수 있나요?\n\nWould it be possible to have another blanket?\n\n우드 잇 비 파시블 투 해브 어나더 블랭킷?\n"},
                {"\n여기는 제 자리입니다.\n\nこれは私の席です\n\n코레와 와타시노 세키데스.\n",
                        "\n짐 올리는 것 좀 도와주세요.\n\n荷物上げる助けてください\n\n니모츠 아게루 테츠다테쿠다사이.\n",
                        "\n혹시 담요 한 장만 더 줄 수 있나요?\n\nもしかしたら毛布一枚だけ与えることがありますか？\n\n모시카시타라 모후 이치마이 다케 아테루 코토가 아리마스카?\n"},
                {"\n여기는 제 자리입니다.\n\n这是我的座位。\n\n쩨 쉬 워 더 쯔 웨이\n",
                        "\n짐 올리는 것 좀 도와주세요.\n\n请帮我抬起行李。\n\n칭 팡 워 타이 치 칭리\n",
                        "\n혹시 담요 한 장만 더 줄 수 있나요?\n\n你能给我一条毯子吗？\n\n니 능 거이 워이 티아오 탄츠마\n"},
                {"\n여기는 제 자리입니다.\n\nCeci est ma place.\n\n스씨 에 마 플라스\n",
                        "\n짐 올리는 것 좀 도와주세요.\n\nS'il vous plaît, aidez-moi à élever mes bagages.\n\n싀 부 풀레 이으디 모아이 아이 일리비 메 베게지\n",
                        "\n혹시 담요 한 장만 더 줄 수 있나요?\n\nPouvez-vous me donner une couverture de plus?\n\n푸헤부 메 두네 운 쿠뱍듀 데 플리스\n"},
                {"\n여기는 제 자리입니다.\n\nEste es mi asiento\n\n에스테 에즈 미 아씨엔또\n",
                        "\n짐 올리는 것 좀 도와주세요.\n\nPor favor ayúdame a subir mi equipaje.\n\n뽀르 파보르 아유다메 아 수비어르 미 에키파떼\n",
                        "\n혹시 담요 한 장만 더 줄 수 있나요?\n\n¿Me puedes dar una manta más?\n\n메 푸에데스 다르 우나 만타 마스?\n"}
        };

        String[][] itemsD_1 = {{"\n택시 승강장이 어디에 있나요?\n\nWhere is a taxi stop?\n\n웨얼 이즈 어 택시 스탑?\n","\n이 주소로 가 주세요\n\nTake me to this address, please\n\n태이크 미 투 디스 어드레스,플리즈\n","\n얼마예요?\n\nHow much is it?\n\n하우 머치 이즈 잇?\n","\n요금이 잘못된 것 같아요\n\nI don't think the fare is right.\n\n아이 돈 띵끄 더 페어 이즈 라잇\n"},
                {"\n택시 승강장이 어디에 있나요?\n\nタクシー乗り場がどこにありますか\n\n타쿠시노리바가 도코니아리마스카\n","\n이 주소로 가 주세요\n\nこの住所まで、お願いします\n\n코노주우쇼마데,오네가이시마스\n","\n얼마예요?\n\nいくらですか\n\n이쿠라데스까\n","\n요금이 잘못된 것 같아요\n\n料金がまちがっているみたいです\n\n료-킨카 마치갓데이루 미타이데스\n"},
                {"\n택시 승강장이 어디에 있나요?\n\n在哪儿可以坐出租车？\n\n짜이 나얼 커이 쭤 추쭈처?\n","\n이 주소로 가 주세요\n\n请到这个地址\n\n칭 따오 저거 띠즈\n","\n얼마예요?\n\n多少钱？\n\n뛰사오 첸?\n","\n요금이 잘못된 것 같아요\n\n车费好像错了\n\n처페이 하오샹 춰 러\n"},
                {"\n택시 승강장이 어디에 있나요?\n\nOù est l'arrêt de taxi?\n\n우에 라헤 드 탁씨?\n","\n이 주소로 가 주세요\n\nJ'aimerais aller à cette adresse\n\n제므헤 알레 아 쎄뜨 아드헤스\n","\n얼마예요?\n\nC'est combien?\n\n쎄 꽁비앙?\n","\n요금이 잘못된 것 같아요\n\nLe prix ne me semble pas correct\n\n르 프히 느 므 썽블르 빠 꼬렉트\n"},
                {"\n택시 승강장이 어디에 있나요?\n\n¿Dónde está la parada de taxi?\n\n돈데 에스따 라 빠라다 데 딱씨?\n","\n이 주소로 가 주세요\n\nLléveme a esta dirección, por favor\n\n예베메 아 에스따 디렉쓰온,뽀르 파보르\n","\n얼마예요?\n\n¿Cuánto es?\n\n꾸안또 에스?\n","\n요금이 잘못된 것 같아요\n\nCreo que la tarifa está mal\n\n끄레오 껠 라 따리파 에스따 말\n"}
        };
        String[][] itemsD_2 = {
                {"\n버스 승강장이 어디에 있나요?\n\nWhere is a bus stop?\n\n웨얼 이즈 어 버스 스탑?\n","\n어떤 버스가 ***까지 가나요\n\nwhich bus goes to the ***\n\n위치 버스 고우스 투 더 ***\n","\n***까지 몇 정거장 남았어요?\n\nHow many stops are left to ***?\n\n하우 메니 스탑스 얼 래프트 투 ***\n"},
                {"\n버스 승강장은 어디에 있나요?\n\nバス乗り場がどこにありますか\n\n바스노리바가 도코니아리마스카\n", "\n어떤 버스가 ***까지 가나요\n\nどのバスが***まで行くんですか\n\n도노 바스가 *** 이키마스카\n","\n***까지 몇 정거장 남았어요?\n\n***まで、バス停はあといくつですか\n\n***마데, 바스테와 아토 이쿠츠데스카\n"},
                {"\n버스 승강장은 어디에 있나요?\n\n在哪儿可以坐公交车\n\n짜이 나얼 커이 쭤 꿍짜오츄?\n","\n어떤 버스가 ***까지 가나요\n\n请问几路公交车去市***\n\n칭원 지 루 꿍쨔오처 취 ***\n","\n***까지 몇 정거장 남았어요?\n\n到***还剩几个站\n\n따오 *** 하이 여우 지 잔?\n"},
                {"\n버스 승강장은 어디에 있나요?\n\nOù est l'arrêt de auto-bus?\n\n우에 라헤 드 어우토 버스?\n\n","\n어떤 버스가 ***까지 가나요\n\nQuel bus va à la ***?\n\n껠 버스 바 아 라 ***\n","\n***까지 몇 정거장 남았어요?\n\nCombien d'arrêt reste-t-il jusqu'à la ***?\n\n꽁비앙 다헤 헤스뜨-띨 쥐스까 라 ***?\n"},
                {"\n버스 승강장은 어디에 있나요?\n\n¿Dónde está la parada de bús?\n\n돈데 에스따 라 빠라다 데 부스?\n","\n어떤 버스가 ***까지 가나요\n\n¿Cuál es el autobús que va al ***?\n\n꾸알 에스 엘 아우또부스 께 바 알 ***?\n","\n***까지 몇 정거장 남았어요?\n\n¿Cuántas paradas quedan para el ayuntamiento?\n\n꾸안따스 빠라다스 께단 빠라 엘 ***?\n"}
        };
        String[][] itemsD_3 = {
                {"\n지하철역은 어디 있습니까?\n\nWhere is th subway station?\n\n웨얼 이즈 더 써브웨이 스테이션?\n","\n***으로 가려면 몇 호선을 타야 해요?\n\nWhich line should I take to get to ***?\n\n위치 라인 쉬드 아이 테이크 투 겟 투 ***?\n","\n***까지 몇 정거장 남았어요?\n\nHow many stops are left to ***?\n\n하우 메니 스탑스 얼 래프트 투 ***\n", "\n***으로 가려면 몇 번 출구로 나가야 해요?\n\nWhich exit will lead me to ***?\n\n위치 엑씨트 윌 리드 미 투 ***?\n"},
                {"\n지하철역은 어디 있습니까?\n\n地下鉄の駅はどこですか\n\n치카테츠노 에키와 도코데스카\n","\n***으로 가려면 몇 호선을 타야 해요?\n\n***に行くには何号線に乗ったらいいですか\n\n***니 이쿠니와 나니센니 놋타라 이데스카\n","\n***까지 몇 정거장 남았어요?\n\n***まで、バス停はあといくつですか\n\n***마데, 바스테와 아토 이쿠츠데스카\n", "\n***으로 가려면 몇 번 출구로 나가야 해요?\n\n***は、何番出口ですか\n\n***와 난반데구치데스카\n"},
                {"\n지하철역은 어디 있습니까?\n\n地铁站在哪里\n\n띠톄잔 자나일?\n","\n***으로 가려면 몇 호선을 타야 해요?\n\n想去***要坐几号线?\n\n시양추 *** 랴오주워 지 하오치엔?\n","\n***까지 몇 정거장 남았어요?\n\n到***还剩几个站\n\n따오 *** 하이 여우 지 잔?\n", "\n***으로 가려면 몇 번 출구로 나가야 해요?\n\n去***要从几号出口出去?\n\n취 *** 야오 충 지 하오 추커우 추취?\n"},
                {"\n지하철역은 어디 있습니까?\n\nOù est la station de métro?\n\n우 에 라 스따씨옹 드 메트로?\n","\n***으로 가려면 몇 호선을 타야 해요?\n\nQuelle ligne doit-on prendre pour aller à ***?\n\n껠 리뉴 드와-똥 프헝드흐 뿌흐 알레 아 ***?\n","\n***까지 몇 정거장 남았어요?\n\nCombien d'arrêt reste-t-il jusqu'à la ***?\n\n꽁비앙 다헤 헤스뜨-띨 쥐스까 라 ***?\n", "\n***으로 가려면 몇 번 출구로 나가야 해요?\n\nQuelle sortie dois-je prendre pour aller au ***?\n\n껠 소흐띠 드와-즈 프헝드흐 뿌흐 알레 오 ***?\n"},
                {"\n지하철역은 어디 있습니까?\n\n¿Dónde está la estación de metro?\n\n돈데 에스따 라 에스따씨온 데 메트로?\n","\n***으로 가려면 몇 호선을 타야 해요?\n\n¿Qué línea hay que tomar para ir a la ***?\n\n께 리네아 아이 께 또마르 빠라 이르 알 라***?\n","\n***까지 몇 정거장 남았어요?\n\n¿Cuántas paradas quedan para el ayuntamiento?\n\n꾸안따스 빠라다스 께단 빠라 엘 ***?\n", "\n***으로 가려면 몇 번 출구로 나가야 해요?\n\nPara ir al ***, ¿por qué número de salida hay que salir\n\n빠라 이르 알 **8, 뽀르 께 누메로 데 살리다 아이께 살리르?\n"}
        };
        String[][] itemsD_4 = {
                {"\n~으로 가는 편도 티켓 주세요\n\nI'd like one one-way ticket to ~\n\n아이드 라이크 원 원웨이 티켓 투 ~\n","\n얼마인가요?\n\nHow much is it?\n\n하우 머치 이즈 잇?\n","\n이거 ~행이에요?\n\nIs this for ~?\n\n이즈 디스 포 ~?\n","\n이번 역이 무슨 역인가요?\n\nWhat station is this?\n\n왓 스테이션 이즈 디스?\n"},
                {"\n~으로 가는 편도 티켓 주세요\n\n~行きの片道一枚ください\n\n~이키노카타미치이치마이쿠다사이. \n","\n얼마예요?\n\nいくらですか\n\n이쿠라데스까\n","\n이거 ~행이에요?\n\nこれ、 ~行きですか\n\n고레 ~유키데스카?\n","\n이번 역이 무슨 역인가요?\n\n次は、何駅ですか\n\n츠기와 나니에키데스카?\n"},
                {"\n~으로 가는 편도 티켓 주세요\n\n请给我一张~海的单程票\n\n칭 게이 워 이 장 취 ~ 더 딴청퍄오\n","\n얼마예요?\n\n多少钱？\n\n뛰사오 첸?","\n이거 ~행이에요?\n\n这是去~的吗?\n\n저 스 취 ~ 더 마?\n","\n이번 역이 무슨 역인가요?\n\n这站是哪一站？\n\n저 잔 스 나 이 잔?\n"},
                {"\n~으로 가는 편도 티켓 주세요\n\nUn aller simple pour ~, s'il vous plaît\n\n아 날레 쌍쁠르 뿌흐 ~, 씰 부 쁠레\n","\n얼마예요?\n\nC'est combien?\n\n쎄 꽁비앙?\n","\n이거 ~행이에요?\n\nEst-ce que ce train part pour ~?\n\n에스끄 쓰 트랑 빠흐 뿌흐 ~?\n","\n이번 역이 무슨 역인가요?\n\nÀ quelle station sommes-nous? \n\n아 껠 쓰따씨옹 썸 누?\n"},
                {"\n~으로 가는 편도 티켓 주세요\n\nDéme un billete de ida para ~, por favor\n\n데메 운 비예떼 데 이다 빠라 ~,뽀르 파보르\n","\n얼마예요?\n\n¿Cuánto es?\n\n꾸안또 에스?\n","\n이거 ~행이에요?\n\n¿Éste va en dirección ~?\n\n에스떼 바 엔 디렉씨온  ~?\n","\n이번 역이 무슨 역인가요?\n\n¿Qué parada es ésta? \n\n께 빠라다 에스 에스따?\n"}
        };
        String[][] itemsD_5 = {
                {"\n차를 빌리고 싶어요\n\nI'd like to rent a car\n\n아잇 라익 투 렌트 어 카\n","\n네비게이션도 빌려주세요\n\nI'd like to rent a GPS, too\n\n아잇 라익 투 렌트 어 지피에스, 투\n","\n전체 요금이 얼마인가요?\n\nHow much does it cost in all?\n\n하우 머치 더즈 잇 코스트 인 올?\n","\n모든 보험을 다 들겟습니다\n\nSign me up for everything\n\n싸인 미 업 포 에브리씽\n" },
                {"\n차를 빌리고 싶어요\n\n車をレンタルしたいんですが\n\n구루마오 렌타루 시타인데스가\n","\n네비게이션도 빌려주세요\n\nカーナビもいっしょに貸してください\n\n카-나비모 잇쇼니 가시테 구다사이\n","\n전체 요금이 얼마인가요?\n\n全部でいくらですか\n\n젠부데 이쿠라데스카?\n","\n모든 보험을 다 들겟습니다\n\n保険は全部加入します\n\n호켄와 젠부 가뉴-시마스\n"},
                {"\n차를 빌리고 싶어요\n\n我想租车\n\n워 샹 쭈 처\n","\n네비게이션도 빌려주세요\n\n导航仪也一起租\n\n다오항이 예 이치 쭈\n","\n전체 요금이 얼마인가요?\n\n全部费用一共多少钱？\n\n취앤부 페이융 이꿍 뚸사오 첸?\n","\n모든 보험을 다 들겟습니다\n\n所有的保险都入\n\n쒀여우 더 바오센 떠오 루\n"},
                {"\n차를 빌리고 싶어요\n\nJe voudrais louer une voiture. \n\n즈 부드헤 루에 윈 브와뛰흐\n","\n네비게이션도 빌려주세요\n\nJe vais aussi louer un GPS, s'il vous plaî\n\n즈 베 오씨 루에 앙 제뻬에스, 씰 부 쁠레\n","\n전체 요금이 얼마인가요?\n\ntQuel est le prix total? \n\n껠 에 르 프리 또딸?\n","\n모든 보험을 다 들겟습니다\n\nL'assurance tout risque, s'il vous plaît\n\n껠 아쒸헝스 불레-부 프헝드흐?\n"},
                {"\n차를 빌리고 싶어요\n\nQuisiera alquilar un coche\n\nㅍ끼시에라 알낄라르 운 꼬체\n","\n네비게이션도 빌려주세요\n\nQuiero alquilar también el sistema de navegación\n\n끼에로 알낄라르 땀비엔 엘 시스떼마 데 나베가씨온\n","\n전체 요금이 얼마인가요?\n\n¿Cuánto es el precio total? \n\n꾸안또 에스 엘 쁘레씨오 또딸?\n","\n모든 보험을 다 들겟습니다\n\nQuiero el seguro contra todo riesgo\n\n끼에로 엘 세구로 꼰뜨라 또도 리에스고\n"}
        };

        String[][] itemsE_1 = {
                {"\n야채 코너는 어디있나요?\n\nWhere is the produce section?\n\n웨어 이즈 더 프로듀스 섹션?\n","\n쇼핑 카트는 어디에 있어요?\n\nWhere are the shopping carts?\n\n웨어 아 더 쇼핑 카트?\n","\n한국 상품을 팔고 있나요?\n\nCan I buy Korean products here?\n\n캔 아이 바이 코리안 프러덕츠 히어?\n"},
                {"\n야채 코너는 어디있나요?\n\n野菜売場ばはどこですか\n\n야사이 우리바와 도코데스카?\n","\n쇼핑 카트는 어디에 있어요?\n\nショッピングカートはどこにありますか\n\n숏핀구카-토와 도코니 아리마스카?\n","\n한국 상품을 팔고 있나요?\n\n韓国の品物を売っていますか\n\n간코쿠노 시나모노오 웃테이마스카?\n"},
                {"\n야채 코너는 어디있나요?\n\n蔬菜柜台在哪儿？ \n\n수차이 꾸이타이 짜이 나얼?\n","\n쇼핑 카트는 어디에 있어요?\n\n购物车在哪儿？ \n\n꺼우우처 짜이 나얼?\n","\n한국 상품을 팔고 있나요?\n\n有韩国商品吗？\n\n여우 한궈 상핀 마?\n"},
                {"\n야채 코너는 어디있나요?\n\nOù est le rayon des légumes? \n\n우 에 르 헤용 데 레귐?\n","\n쇼핑 카트는 어디에 있어요?\n\nOù sont les chariots de supermarché? \n\n우 쏭 레 샤히오 드 쒸뻬흐마흐셰?\n","\n한국 상품을 팔고 있나요?\n\nVendez-vous des produits coréens? \n\n벙데 부 데 프호뒤 꼬헤앙?\n"},
                {"\n야채 코너는 어디있나요?\n\n¿Dónde está la sección de verduras? \n\n돈데 에스따 라 섹씨온 데 베르두라스?\n","\n쇼핑 카트는 어디에 있어요?\n\n¿Dónde están los carritos de compras? \n\n돈데 에스딴 로스 까리또스 데 꼼쁘라스?\n","\n한국 상품을 팔고 있나요?\n\n¿Vende productos coreanos? \n\n벤데 쁘로둑또스 꼬레아노스?\n"}
        };
        String[][] itemsE_2 = {
                {"\n이 책을 찾아 주세요 \n\nCould you find this book for me? \n\n쿠쥬 파인드 디스 북 포 미?\n","\n이 도시의 지도가 있어요? \n\nDo you have a map of the city? \n\n두 유 햅 어 맵 업 더 시티?\n","\n볼펜 있어요? \n\nDo you have ballpoint pens? \n\n두 유 햅 볼포인트 펜즈?  \n","\n볼펜 좀 써 볼게요\n\nCan I try this pen out? \n\n캔 아이 튜라이 디스 펜 아웃?\n"},
                {"\n이 책을 찾아 주세요 \n\nこの本を探してください\n\n고노 혼오 사가시테 구다사이\n","\n이 도시의 지도가 있어요? \n\nこの都市の地図、ありますか\n\n고노 도시노 치즈, 아리마스카?\n","\n볼펜 있어요? \n\nボールペンありますか\n\n보-루펜 아리마스카?\n","\n볼펜 좀 써 볼게요\n\n少し試してみますね\n\n스코시 다메시테미마스네  \n"},
                {"\n이 책을 찾아 주세요 \n\n请给我找一下这本书\n\n칭 게이 워 자오 이샤 저 번 수\n","\n이 도시의 지도가 있어요? \n\n有这个城市的地图吗？\n\n여우 저거 청스 더 띠투 마?\n","\n볼펜 있어요? \n\n有圆珠笔吗？\n\n여우 위앤주비 마?\n","\n볼펜 좀 써 볼게요\n\n圆珠笔我试一下\n\n위앤주비 워 스 이샤\n"},
                {"\n이 책을 찾아 주세요 \n\nPourriez-vous me trouver ce livre? \n\n뿌히에 부 므 트후베 쓰 리브흐?\n","\n이 도시의 지도가 있어요? \n\nAvez-vous un plan de cette ville? \n\n아베 부 앵 쁠렁 드 쎄뜨 빌?\n","\n볼펜 있어요? \n\nAvez-vous des stylos? \n\n아베 부 데 스띨로?\n","\n볼펜 좀 써 볼게요\n\nEst-ce que je peux essayer des stylos à bille? \n\n에스끄 즈 쁘 에쎄이에 데 스띨로 아 비으?\n"},
                {"\n이 책을 찾아 주세요 \n\nBúsqueme este libro, por favor. \n\n부스께메 에스떼 리브로, 뽀르 파보르\n","\n이 도시의 지도가 있어요? \n\n¿Tiene un mapa de esta ciudad? \n\n띠에네 운 마빠 데 에스따 씨우닫?\n","\n볼펜 있어요? \n\n¿Tiene bolígrafos? \n\n띠에네 볼리그라포스?\n","\n볼펜 좀 써 볼게요\n\n¿Puedo probar este bolígrafo? \n\n뿌에도 쁘로바르 에스떼 볼리그라포?\n"}
        };
        String[][] itemsE_3 = {
                {"\n더 작은/큰 사이즈가 있나요?\n\nI want a (smaller/bigger) size\n\n아이 원트 어 (스몰러/비거) 사이즈\n","\n입어봐도 되나요?\n\ncan i try this?\n\n캔 아이 트라이 디스?\n","\n탈의실은 어디인가요?\n\nWhere is the fitting room? \n\n웨얼 이즈 어 피팅 룸\n"},
                {"\n더 작은/큰 사이즈가 있나요?\n\nもう少し(小さい/大きな)サイズください\n\n모- 스코시 (치-사이/오오키나) 사이즈 구다사이\n","\n입어봐도 되나요?\n\n着てみてもいいですか\n\n기테미테모 이-데스카?\n","\n탈의실은 어디인가요?\n\n試着室はどこですか\n\n시차쿠시츠와 도코데스카?\n"},
                {"\n더 작은/큰 사이즈가 있나요?\n\n请给我再(小/大)一点的\n\n칭 게이 워 짜이 (샤오/다오) 이덴 더\n","\n입어봐도 되나요?\n\n可以试穿吗？ \n\n커이 스촨 마?\n","\n탈의실은 어디인가요?\n\n试衣间在哪儿？ \n\n스이젠 짜이 나얼?\n"},
                {"\n더 작은/큰 사이즈가 있나요?\n\nJe voudrais une taille plus (petite/grand).\n\n즈 부드해 윈 따이으 쁠뤼 (쁘띠뜨/그란드)\n","\n입어봐도 되나요?\n\nJe peux l'essayer? \n\n즈 쁘 레쎄이에?\n","\n탈의실은 어디인가요?\n\nOù est la cabine d'essayage? \n\n우 에 라 꺄빈 데쎄이야쥬?\n"},
                {"\n더 작은/큰 사이즈가 있나요?\n\nDeme una talla un poco más (pequeña/bulto). \n\n데메 우나 따야 움 뽀꼬 마스 (뻬께냐/불또)\n","\n입어봐도 되나요?\n\n¿Puedo probármelo?\n\n뿌에도 쁘로바르멜로?\n","\n탈의실은 어디인가요?\n\n¿Dónde está el probador? \n\n돈데 에스따 엘 쁘로바도르?\n"}
        };
        String[][] itemsE_4 = {
                {"\n선물로 괜찮은 것을 보여주세요\n\nCould you recommend something for a gift? \n\n쿠쥬 레커멘드 썸씽 포러 기프트?\n","\n다른 것은 없어요?\n\nCan I see other items? \n\n캔 아이 씨 어더 아이템즈?\n","\n가장 인기 있는 건 어떤 거예요?\n\nWhat is the most popular ? \n\n왓 이즈 더 모스트 파퓰러?  \n"},
                {"\n선물로 괜찮은 것을 보여주세요\n\nプレゼントでいいことを見せてください\n\n푸레젠토데이 이코토 오미세테 쿠다사이 \n","\n다른 것은 없어요?\n\nほかのはありませんか \n\n호카노와 아리마센카?\n","\n가장 인기 있는 건 어떤 거예요?\n\nいちばん人気があるのは、どれですか\n\n이치반 닌키가 아루노와 도레데스카?\n"},
                {"\n선물로 괜찮은 것을 보여주세요\n\n请给我看一下好一点的礼物\n\n칭 게이 워 칸 이샤 하오 이덴 더 리우\n","\n다른 것은 없어요?\n\n没有别的了吗？\n\n메이여우 볘더 러 마?\n","\n가장 인기 있는 건 어떤 거예요?\n\n卖得最好的是哪个商品？\n\n마이 더 쭈이하오 더 스 나거 상핀?\n"},
                {"\n선물로 괜찮은 것을 보여주세요\n\nJe cherche un bon cadeau \n\n즈 셰흐슈 앙 봉 까도\n","\n다른 것은 없어요?\n\nY-a-t-il autre chose? \n\n이아띨 오트흐 쇼즈?\n","\n가장 인기 있는 건 어떤 거예요?\n\nQuel est le produit le plus vendu? \n\n껠 에 르 프호뒤 르 쁠뤼 벙뒤?\n"},
                {"\n선물로 괜찮은 것을 보여주세요\n\n¿Me enseña algo para regalo\n\n메 엔세냐 알고 빠라 레갈로 데 보다\n","\n다른 것은 없어요?\n\n¿No hay otra cosa?\n\n노 아이 오뜨라 꼬사?\n","\n가장 인기 있는 건 어떤 거예요?\n\n¿Cuál es el más popular? \n\n꾸알 에스 엘 마스 뽀뿔라르?\n"}
        };
        String[][] itemsE_5 = {
                {"\n이건 얼마예요? \n\nHow much is it? \n\n하우 머치 이즈 잇?\n","\n이 가격이 할인 가격이에요?\n\nIs this the sale price?\n\n이즈 디스 더 쎄일 프라이스?\n","\n할인해 주시겠어요? \n\nCould you give me a discount? \n\n쿠쥬 깁 미어 디스카운트?\n"},
                {"\n이건 얼마예요? \n\nこれ、いくらですか\n\n고레 이쿠라데스카?\n","\n이 가격이 할인 가격이에요?\n\nこれはセール価格ですか\n\n고레와 세-루카카쿠데스카?\n","\n할인해 주시겠어요? \n\n割引してくれませんか\n\n와리비키 시테 구레마센카?\n"},
                {"\n이건 얼마예요? \n\n这个多少钱？\n\n저거 뚸사오 첸?\n","\n이 가격이 할인 가격이에요?\n\n这个价格是打折价格吗？\n\n저거 쟈거 스 다저 쟈거 마?\n","\n할인해 주시겠어요? \n\n能打折吗？\n\n넝 다저 마?\n"},
                {"\n이건 얼마예요? \n\nÇa fait combien?\n\n싸 페 꽁비앙?\n","\n이 가격이 할인 가격이에요?\n\nEst-ce que c'est le prix soldé?\n\n에스끄 쎄 르 프리 쏠데?\n","\n할인해 주시겠어요? \n\nVous me faites une réduction? \n\n부 므 페뜨 윈 헤뒥씨옹?\n"},
                {"\n이건 얼마예요? \n\n¿Cuánto cuesta esto? \n\n꾸안또 꾸에스따 에스또?\n","\n이 가격이 할인 가격이에요?\n\n¿Es este el precio rebajado?\n\n에스 에스떼 엘 쁘레씨오 레바하도?\n","\n할인해 주시겠어요? \n\n¿Me puede hacer un descuento? \n\n메 뿌에데 아쎄르 운 데스꾸엔또?\n"}
        };
        String[][] itemsE_6 = {
                {"\n어디에서 계산해요?\n\nWhere do I pay?\n\n웨어 두 아이 페이?\n","\n봉지 주세요\n\nCan I have a bag?\n\n캔 아이 햅 어 백?\n","\n(선물용으로),(따로따로) 포장해 주세요\n\nPlease wrap this(for a gift),(separately) \n\n플리즈 랩 디스(폴 어 기프트),(세퍼레틀리)\n","\n신용 카드로 계산해도 돼요?\n\nCan I pay by credit card?\n\n캔 아이 페이 바이 크레딧 카드?\n"},
                {"\n어디에서 계산해요?\n\n会計はどこですか\n\n가이케-와 도코데스카?\n","\n봉지 주세요\n\n袋ください\n\n후쿠로 구다사이\n","\n(선물용),(따로따로) 포장해 주세요\n\n(贈り物の)包装),(別々べつべつに)してください\n\n(오쿠리모노노),(베츠베츠니)호-소-시테 구다사이\n","\n신용 카드로 계산해도 돼요?\n\nクレジットカードも使えますか\n\n쿠레짓토카-도모 츠카에마스카?   \n"},
                {"\n어디에서 계산해요?\n\n在哪儿付款?\n\n짜이 나얼 푸 콴?\n","\n봉지 주세요\n\n请给我一个袋子\n\n칭 게이 워 이 꺼 따이쯔\n","\n(선물),(따로따로) 포장해 주세요\n\n请给我(礼物),(分别)包装一下\n\n칭 게이 워(리우),(펀볘) 빠오좡 이샤\n","\n신용 카드로 계산해도 돼요?\n\n可以用信用卡付款吗？\n\n커이 융 신융카 푸 콴 마?\n"},
                {"\n봉지 주세요\n\nUn sac, s'il vous plaît. \n\n앙 싸끄, 씰 부 쁠레\n","\n어디에서 계산해요?\n\nOù est ce que je peux régler?\n\n우 에스끄 즈 쁘 헤글레?\n","\n(선물),(따로따로) 포장해 주세요\n\nPourriez-vous l'emballer(for Cadeau),(séparément) \n\n뿌히에-부 렁발레(포 카도),(쎄빠헤멍)\n","\n신용 카드로 계산해도 돼요?\n\nEst-ce que je peux payer avec ma carte de crédit? \n\n에스끄 즈 쁘 뻬이에 아베끄 마 까흐뜨 드 크헤디?\n"},
                {"\n어디에서 계산해요?\n\n¿Dónde hay que pagar?\n\n돈데 아이 께 빠가르?\n","\n봉지 주세요\n\nDéme una bolsa. \n\n데메 우나 볼사\n","\n(선물),(따로따로) 포장해 주세요\n\nEnvuélvamelo(para),(por separado), por favor. \n\n엔부엘바멜로(빠라),(뽀르 세빠라도), 뽀르 파보르\n","\n신용 카드로 계산해도 돼요?\n\n¿Se puede pagar con tarjeta de crédito? \n\n세 뿌에데 빠가르 꼰 따르헤따 데 끄레디또?\n"}
        };
        String[][] itemsE_7 = {
                {"\n이걸 교환하고 싶어요\n\nI'd like to exchange this, please\n\n아잇 라익 투 익스체인지 디스, 플리즈\n","\n더 작은/큰 치수로 바꾸고 싶어요\n\nI want to exchange it for a smaller/bigger size\n\n아이 원 투 익스체인지 잇 포러 스몰러/비거 사이즈\n","\n여기에 흠집이 있어요\n\nThe product is flawed\n\n더 프러덕트 이즈 플로드\n","\n이걸 반품하고 싶어요\n\nI want to return it\n\n아이 원 투 리턴 잇\n"},
                {"\n이걸 교환하고 싶어요\n\nこれを、交換したいんですが\n\n고레오 고-칸시타인데스가\n","\n더 작은/큰 치수로 바꾸고 싶어요\n\nもっと(小さい/大きな) サイズに交換したいんですが\n\n못토 (치-사이/오오키나) 사이즈니 고-칸시타인데스가\n","\n여기에 흠집이 있어요\n\nここにキズがあります\n\n고코니 기즈가 아리마스\n","\n이걸 반품하고 싶어요\n\nこれを返品したいんですが\n\n고레오 헨핀시타인데스가\n"},
                {"\n이걸 교환하고 싶어요\n\n我想把这个换一下\n\n워 샹 바 저거 환 이샤\n","\n더 작은/큰 치수로 바꾸고 싶어요\n\n我想换个(小/大)一点的\n\n워 샹 환 꺼 (샤오/다오) 이덴 더\n","\n여기에 흠집이 있어요\n\n这里有瑕疵\n\n저리 여우 샤츠\n","\n이걸 반품하고 싶어요\n\n这个我想退货\n\n저거 워 샹 투이훠\n"},
                {"\n이걸 교환하고 싶어요\n\nJe voudrais échanger ceci\n\n즈 부드헤 에셩제 쓰씨\n","\n더 작은/큰 치수로 바꾸고 싶어요\n\nJe voudrais l'échanger contre une taille plus (petite/grand)\n\n즈 부드헤 레셩제 꽁트르 윈 따이유 쁠뤼 쁘띠뜨\n","\n여기에 흠집이 있어요\n\nIl y a des défauts\n\n일리아 데 데포\n","\n이걸 반품하고 싶어요\n\nJe voudrais retourner ce produit\n\n즈 부드헤 흐뚜흐네 쓰 프호뒤\n"},
                {"\n이걸 교환하고 싶어요\n\nQuisiera cambiar esto\n\n끼시에라 깜비아르 에스또\n","\n더 작은/큰 치수로 바꾸고 싶어요\n\nQuiero cambiarlo por una talla más (pequeña/bulto)\n\n끼에로 깜비아를로 뽀르 우나 따야 마스 (뻬께냐/불또)\n","\n여기에 흠집이 있어요\n\nAquí hay un defecto\n\n아끼 아이 운 데펙또\n","\n이걸 반품하고 싶어요\n\nQuisiera devolverlo\n\n끼시에라 데볼베를로\n"}
        };

        String[][] itemsF_1 = {
                {"\n예약하고 싶습니다\n\nI'd like to make a reservation\n\n아잇 라익 투 메익 어 레저베이션\n","\n방값은 얼마예요?\n\nHow much is the room? \n\n하우 머치 이즈 더 룸?\n","\n1/2/4인실 부탁합니다\n\nI'd like a (single/double/quadruplet), please. \n\n아잇 라익 어 (싱글/더블/콰드루펄트, 플리즈\n","\n오늘 밤에 잘 수 있는 방 있어요? \n\nDo you have a room for tonight? \n\n두 유 햅 어 룸 포 트나잇?\n"},
                {"\n예약하고 싶습니다\n\n予約をしたいんですが\n\n요야쿠오 시타인데스가\n","\n방값은 얼마예요?\n\n宿泊料はいくらですか\n\n슈쿠하쿠료-와 이쿠라데스카?\n","\n1/2/4인실 부탁합니다\n\n(1/2/4)人部屋、お願いします\n\n(히토리/후타리/욧닌)베야 오네가이시마스\n","\n오늘 밤에 잘 수 있는 방 있어요? \n\n今夜泊まれる部屋、ありますか\n\n곤야 도마레루 헤야 아리마스카?\n"},
                {"\n예약하고 싶습니다\n\n我想预订\n\n워 샹 위띵\n","\n방값은 얼마예요?\n\n房费是多少？\n\n팡페이 스 뚸사오?\n","\n1/2/4인실 부탁합니다\n\n我想要(单人单间/标准间/4人间)\n\n워 샹야오 (단전단지엔/뱌오준젠/쓰전지엔\n","\n오늘 밤에 잘 수 있는 방 있어요? \n\n有今天晚上能住的房间吗？\n\n여우 진텐 완상 넝 주 더 팡젠 마?\n"},
                {"\n예약하고 싶습니다\n\nJe voudrais réserver\n\n즈 부드헤 헤제흐베\n","\n방값은 얼마예요?\n\nCombien coûte une chambre? \n\n꽁비앙 꾸뜨 윈 셩브흐?\n","\n1/2/4인실 부탁합니다\n\n(Une chambre simple/Une chambre pour deux personnes/Quatrième lieu), s'il vous plaît\n\n(윈 셩브흐 셤프/윈 셩브흐 뿌흐 두 뻬흐쏜느/끄와뜨리완 류), 씰 부 쁠레\n","\n오늘 밤에 잘 수 있는 방 있어요? \n\nEst-ce qu'il y a une chambre disponible pour cette nuit? \n\n에스낄리아 윈 셩브흐 디스뽀니블르 뿌흐 쎄뜨 뉘?\n"},
                {"\n예약하고 싶습니다\n\nQuisiera hacer una reserva\n\n끼시에라 아쎄르 우나 레세르바\n","\n방값은 얼마예요?\n\n¿Cuánto es la tarifa de la habitación?\n\n꾸안또 에스 라 따리파 델 라 아비따씨온?\n","\n1/2/4인실 부탁합니다\n\nHabitación (simple/doble/cuatro), por favor.\n\n아비따씨온 (심플레/도블레/쿠아뜨로, 뽀르 파보르\n","\n오늘 밤에 잘 수 있는 방 있어요? \n\n¿Hay alguna habitación disponible para esta noche? \n\n아이 알구나 아비따씨온 디스뽀니블레 빠라 에스따 노체?\n"}
        };
        String[][] itemsF_2 = {
                {"\n제 방을 청소해 주세요\n\nMy room needs to be cleaned, please\n\n마이 룸 니즈 투 비 클린드, 플리즈\n","\n다른 방으로 바꿔주세요\n\nI'd like to change rooms, please\n\n아잇 라익 투 체인지 룸스, 플리즈\n","\n수건을 더 주세요\n\nCould I have more towels? \n\n쿠다이 햅 모어 타월즈?\n","\n아침 식사는 언제 할 수 있어요?\n\nWhen do you serve breakfast?\n\n웬 두 유 써브 브렉퍼스트?\n"},
                {"\n제 방을 청소해 주세요\n\n部屋の掃除をしてください\n\n헤야노 소-지오 시테 구다사이\n","\n다른 방으로 바꿔주세요\n\nほかの部屋に替えてください\n\n호카노 헤야니 가에테 구다사이\n","\n수건을 더 주세요\n\nタオルをもう少しください\n\n타오루오 모- 스코시 구다사이\n","\n아침 식사는 언제 할 수 있어요?\n\n朝食は、いつできますか\n\n초-쇼쿠와 이츠 데키마스카?\n"},
                {"\n제 방을 청소해 주세요\n\n请把我的房间打扫一下\n\n칭 바 워 더 팡젠 다싸오 이샤\n","\n다른 방으로 바꿔주세요\n\n请给我换一个房间\n\n칭 게이 워 환 이 꺼 팡젠\n","\n수건을 더 주세요\n\n请再给我几条毛巾\n\n칭 짜이 게이 워 지 탸오 마오진\n","\n아침 식사는 언제 할 수 있어요?\n\n早餐什么时候能吃？\n\n짜오찬 선머 스허우 넝 츠?\n"},
                {"\n제 방을 청소해 주세요\n\nNettoyez la chambre, s'il vous plaît.\n\n네뜨와이에 라 셩브흐, 씰 부 쁠레\n","\n다른 방으로 바꿔주세요\n\nChangez-moi de chambre, s'il vous plaît\n\n셩제-므와 드 셩브흐, 씰 부 쁠레\n","\n수건을 더 주세요\n\nPlus de serviettes, s'il vous plaît\n\n쁠뤼스 드 쎄흐비에뜨, 씰 부 쁠레\n","\n아침 식사는 언제 할 수 있어요?\n\nQuand est-ce que je peux prendre le petit-déjeuner? \n\n껑 떼스끄 즈 쁘 프헝드흐 르 쁘띠 데주네?\n"},
                {"\n제 방을 청소해 주세요\n\n¿Puede limpiar mi habitación, por favor? \n\n뿌에데 림삐아르 미 아비따씨온, 뽀르 파보르?\n","\n다른 방으로 바꿔주세요\n\nCámbieme de habitación, por favor\n\n깜비에메 데 아비따씨온, 뽀르 파보르\n","\n수건을 더 주세요\n\n¿Me da más toallas, por favor? \n\n메 다 마스 또아야스, 뽀르 파보르?\n","\n아침 식사는 언제 할 수 있어요?\n\n¿Cuándo se puede desayunar? \n\n꾸안도 세 뿌에데 데사유나르?\n"}
        };
        String[][] itemsF_3 = {
                {"\n체크인 하겠습니다\n\nI'd like to check in\n\n아잇 라익 투 체크인\n","\n제 이름으로 예약했습니다\n\nIt's in my name\n\n잇츠 인 마이 네임\n","\n체크아웃 할게요\n\nI'd like to check out\n\n아잇 라익 투 체크 아웃\n","\n저녁까지 제 짐을 보관해 주실 수 있어요?\n\nCould you keep my luggage until this evening?\n\n쿠쥬 킵 마이 러기지 언틸 디스 이브닝?\n","\n택시를 불러주세요\n\nCould you call me a taxi? \n\n쿠쥬 콜 미 어 택시?\n"},
                {"\n체크인 하겠습니다\n\nチェックイン、お願いします\n\n쳇쿠인 오네가이시마스\n","\n제 이름으로 예약했습니다\n\n私わたしの名前で予約しました\n\n와타시노 나마에데 요야쿠시마시타\n","\n체크아웃 할게요\n\nチェックアウトは何時までですか\n\n쳇쿠아우토와 난지마데데스카?\n","\n저녁까지 제 짐을 보관해 주실 수 있어요?\n\n\n\n\n","\n택시를 불러주세요\n\n\n\n\n"},
                {"\n체크인 하겠습니다\n\n我想办理入住手续\n\n워 샹 빤리 루주 서우쉬\n","\n제 이름으로 예약했습니다\n\n是用我的名字预订的\n\n스 융 워 더 밍쯔 위띵 더\n","\n체크아웃 할게요\n\n我要退房\n\n워 야오 투이팡\n","\n저녁까지 제 짐을 보관해 주실 수 있어요?\n\n能把我的行李保管到晚上吗？\n\n넝 바 워 더 싱리 바오관따오 완상 마?\n","\n택시를 불러주세요\n\n请给我叫一辆出租车\n\n칭 게이 워 쟈오 이 량 추쭈처\n"},
                {"\n체크인 하겠습니다\n\nJe voudrais faire un check-in\n\n즈 부드헤 페흐 앙 체크인\n","\n제 이름으로 예약했습니다\n\nÀ mon nom\n\n아 몽 농\n","\n체크아웃 할게요\n\nJe voudrais faire mon check-out\n\n즈 부드헤 페흐 몽 체크아웃\n","\n저녁까지 제 짐을 보관해 주실 수 있어요?\n\nPouvez-vous garder mes valises jusqu'à ce soir? \n\n뿌베-부 가흐데 메 발리즈 쥐스까 쓰 쓰와흐?\n","\n택시를 불러주세요\n\nAppelez un taxi, s'il vous plaît.\n\n아쁠레 앙 딱씨, 씰 부 쁠레\n"},
                {"\n체크인 하겠습니다\n\nQuiero hacer check-in\n\n끼에로 아쎄르 체낀\n","\n제 이름으로 예약했습니다\n\nEstá reservado a mi nombre\n\n에스따 레세르바도 아 미 놈브레\n","\n체크아웃 할게요\n\nQuisiera hacer el check-out\n\n끼시에라 아쎄르 엘 체까웃\n","\n저녁까지 제 짐을 보관해 주실 수 있어요?\n\n¿Me puede guardar el equipaje hasta la noche, por favor? \n\n메 뿌에데 구아르다르 엘 에끼빠헤 아스딸 라 노체, 뽀르 파보르?\n","\n택시를 불러주세요\n\nLlámeme un taxi, por favor\n\n야메메 운 딱씨, 뽀르 파보르\n"}
        };

        String[][] itemsG_1 = {
                {"\n여기 다친 사람이 있어요! 구급차를 불러 주세요\n\nSomebody is hurt! Call an ambulance, please! \n\n썸바디 이즈 허트! 콜 언 앰뷸런스, 플리즈!\n","\n여기 의사나 간호사 있어요? \n\nIs there a doctor or nurse here? \n\n이즈 데어 어 닥터 오어 너스 히어?\n","\n의식을 잃고 쓰러졌어요\n\nHe lost consciousness and fainted. \n\n히 로스트 컨셔스니스 앤 페인티드\n","\n이 근처에 병원이 있어요? \n\nIs there a hospital nearby? \n\n이즈 데어 어 하스피털 니어바이?\n"},
                {"\n여기 다친 사람이 있어요! 구급차를 불러 주세요\n\nここにけが人がいます。救急車を呼んでください\n\n고코니 게가닌가 이마스! 규큐-샤오 욘데 구다사이\n","\n여기 의사나 간호사 있어요? \n\nここに医師や看護士、いますか\n\n고코니 이시야 간고시 이마스카?\n","\n의식을 잃고 쓰러졌어요\n\n意識を失って、倒れました\n\n이시키오 우시낫테 다오레마시타\n","\n이 근처에 병원이 있어요? \n\n近くに、病院ありますか\n\n치카쿠니 뵤-인 아리마스카?\n"},
                {"\n여기 다친 사람이 있어요! 구급차를 불러 주세요\n\n这里有人受伤了！请帮忙叫一下救护车\n\n저리 여우 런 서우 상 러! 칭 빵망 쟈오 이샤 쥬후처\n","\n여기 의사나 간호사 있어요? \n\n这里有医生或护士吗？\n\n저리 여우 이성 훠 후스 마?\n","\n의식을 잃고 쓰러졌어요\n\n这个人昏过去了\n\n저거 런 훈 꿔취 러\n","\n이 근처에 병원이 있어요? \n\n这儿附近有医院吗？\n\n저얼 푸진 여우 이위앤 마?\n"},
                {"\n여기 다친 사람이 있어요! 구급차를 불러 주세요\n\nQuelqu'un est blessé! Appelez une ambulance!\n\n껠껭 에 블레쎄! 아쁠레 윈 엉뷜랑스!\n","\n여기 의사나 간호사 있어요? \n\nY a-t-il un médecin ou une infirmière? \n\n이아띨 앵 메드셍 우 윈 앵피흐미에흐?\n","\n의식을 잃고 쓰러졌어요\n\nIl a perdu conscience\n\n일 아 뻬흐뒤 꽁씨엉쓰\n","\n이 근처에 병원이 있어요? \n\nY a-t-il un hôpital près d'ici?\n\n이아띨 앵 오삐딸 프헤 디씨?\n"},
                {"\n여기 다친 사람이 있어요! 구급차를 불러 주세요\n\n¡Aquí hay un herido! ¡Llame a una ambulancia!\n\n아끼 아이 운 에리도 야메 아 우나 암불란씨아!\n","\n여기 의사나 간호사 있어요? \n\n¿Hay aquí algún médico o enfermero?\n\n아이 아끼 알군 메디꼬 오 엔페르메로?\n","\n의식을 잃고 쓰러졌어요\n\nPerdió la conciencia y se desmayó\n\n뻬르디오 라 꼰씨엔씨아 이 세 데스마요\n","\n이 근처에 병원이 있어요? \n\n¿Hay algún hospital por aquí cerca? \n\n¿Hay algún hospital por aquí cerca? \n"}
        };
        String[][] itemsG_2 = {
                {"\n혹시 제 가방 못 보셨어요?\n\nHave you seen my bag? \n\n해뷰 씬 마이 백?\n","\n분실물 취급소/경찰서는 어디에 있어요?\n\nWhere is the (lost and found/police station)? \n\n웨어 이즈 더 로스트 (앤 파운드/폴리스 스테이션)?\n","\n도난 신고를 하고 싶어요\n\nI want to report a theft\n\n아이 원 투 리포트 어 떼프트\n","\n제 지갑을 소매치기 당했어요\n\nMy wallet was stolen\n\n마이 월릿 워즈 스톨른\n","\n경찰을 불러 주세요\n\nCall the police, please\n\n콜 더 폴리스, 플리즈\n"},
                {"\n혹시 제 가방 못 보셨어요?\n\n\n私のカバン、見 かけませんでしたか\n\n와타시노 가반 미카케마센데시타카?\n","\n분실물 취급소/경찰서는 어디에 있어요?\n\n(遺失物センター/警察署)はどこですか\n\n(이시츠부츠 센타/게-사츠쇼)-와 도코데스카?\n","\n도난 신고를 하고 싶어요\n\n盗難届けを出したいんですが\n\n도-난토도케오 다시타인데스가\n","\n제 지갑을 소매치기 당했어요\n\n財布をすりに盗まれました\n\n사이후오 스리니 누스마레마시타\n","\n경찰을 불러 주세요\n\n警察を呼んでください\n\n게-사츠오 욘데 구다사이\n"},
                {"\n혹시 제 가방 못 보셨어요?\n\n\n您看到我的包儿了吗？ \n\n닌 칸따오 워 더 빠오얼 러 마 ?\n","\n분실물 취급소/경찰서는 어디에 있어요?\n\n(失物招领处/公安局)在哪儿？\n\n(스우자오링추/꿍안쥐) 짜이 나얼?\n","\n도난 신고를 하고 싶어요\n\n我想报失\n\n워 샹 빠오 스\n","\n제 지갑을 소매치기 당했어요\n\n我的钱包被偷了\n\n워 더 첸빠오 뻬이 터우 러\n","\n경찰을 불러 주세요\n\n请帮我报警\n\n칭 빵 워 빠오 징\n"},
                {"\n혹시 제 가방 못 보셨어요?\n\n\nQuelqu'un a-t-il vu mon sac?\n\n껠껭 아 띨 뷔 몽 싸끄?\n","\n분실물 취급소/경찰서는 어디에 있어요?\n\nOù est le (bureau des objets trouvés?/ommissariat de police)\n\n우 에 르 (뷔호 데 오브제 트후베?/꼬미싸히아 드 뽈리스)\n","\n도난 신고를 하고 싶어요\n\nJe voudrais faire une déclaration de vol\n\n즈 부드헤 패흐 윈 데끌라하씨옹 드 볼\n","\n제 지갑을 소매치기 당했어요\n\nOn m'a volé mon portefeuille\n\n옹 마 볼레 몽 뽀흐뜨풰이유\n","\n경찰을 불러 주세요\n\nAppelez la police!\n\n아쁠레 라 뽈리쓰\n"},
                {"\n혹시 제 가방 못 보셨어요?\n\n\nPerdón, ¿ha visto mi bolso?\n\n뻬르돈, 아 비스또 미 볼소?\n","\n분실물 취급소/경찰서는 어디에 있어요?\n\n¿Dónde está el centro de objetos (perdidos/policía)? \n\n돈데 에스따 엘 쎈뜨로 데 옵헤또스 (뻬르디도스/뽈리씨아)?\n","\n도난 신고를 하고 싶어요\n\nQuiero hacer una declaración de robo\n\n끼에로 아쎄르 우나 데끌라라씨온 데 로보\n","\n제 지갑을 소매치기 당했어요\n\nMe han robado la cartera\n\n메 안 로바도 라 까르떼라\n","\n경찰을 불러 주세요\n\nLlame a la policía\n\n야메 아 라 뽈리씨아\n"}
        };
        String[][] itemsG_3 = {
                {"\n불이야! 소방서에 전화해요!  \n\nFire! Call the fire department!\n\n파이어! 콜 더 파이어 디파트먼트!\n","\n소화기는 어딨어요? \n\nWhere's the fire extinguisher? \n\n웨어즈 더 파이어 익스팅귀셔?\n","\n빨리 피해요!\n\nRun! \n\n런!\n"},
                {"\n불이야! 소방서에 전화해요!  \n\n火事だ！　消防署に連絡してください\n\n가지다! 쇼-보-쇼니 렌라쿠시테 구다사이\n","\n소화기는 어딨어요? \n\n消火器はどこですか\n\n쇼-카키와 도코데스카?\n","\n빨리 피해요!\n\n早く逃げましょう\n\n하야쿠 니게마쇼\n"},
                {"\n불이야! 소방서에 전화해요!  \n\n着火了！快给消防队打电话！\n\n자오 훠 러! 콰이 게이 샤오팡뚜이 다 뗀화!\n","\n소화기는 어딨어요? \n\n灭火器在哪儿？\n\n몌훠치 짜이 나얼?\n","\n빨리 피해요\n\n快躲开！\n\n콰이 둬카이!\n"},
                {"\n불이야! 소방서에 전화해요!  \n\nAu feu! Appelez les pompiers! \n\n오 푸! 아쁠레 레 뽕삐에!\n","\n소화기는 어딨어요? \n\nOù est l'extincteur? \n\n우 에 렉스땡뙤흐?\n","\n빨리 피해요!\n\nMettez-vous à l'abri! \n\n메떼 부 아 라브히\n"},
                {"\n불이야! 소방서에 전화해요!  \n\n¡Fuego! ¡Llamen a los bomberos! \n\n푸에고! 야멘 알 로스 봄베로스!\n","\n소화기는 어딨어요? \n\n¿Dónde está el extintor? \n\n돈데 에스따 엘 엑쓰띵또르?\n","\n빨리 피해요!\n\n¡Hay que evacuar! ¡Rápido!\n\n아이 께 에바꾸아르! 라삐도!\n"}
        };
        String[][] itemsG_4 = {
                {"\n당신, 괜찮아요? \n\nOh my goodness! Are you all right? \n\n오 마이 굿니스! 아 유 올라잇?\n","\n경찰을 불러 주세요\n\nCall the police\n\n콜 더 폴리스\n","\n충돌 사고를 당했어요\n\nI've had a car accident\n\n아입 해더 카 액시던트\n ","\n제가 교통사고를 냈어요\n\nI made a car accident\n\n아이 메이드 어 카 엑시덴트\n","\n죄송해요. 바뀐 신호를 못 봤어요 \n\nI'm sorry. I didn't see the light change\n\n아임 쏘리. 아이 디든 씨 더 라잇 체인지\n"},
                {"\n당신, 괜찮아요? \n\n大丈夫ですか\n\n다이죠부데스카?\n","\n경찰을 불러 주세요\n\n警察を呼んでください\n\n게-사츠오 욘데 구다사이\n","\n충돌 사고를 당했어요\n\n衝突事故にあいました\n\n쇼-토츠지코니 아이마시타\n ","\n제가 교통사고를 냈어요\n\n私が、交通事故を起こしました\n\n와타시가 고-츠-지코오 오코시마시타\n","\n죄송해요. 바뀐 신호를 못 봤어요 \n\nすみません。 信号が変わったのに気きづませんでした\n\n스미마센. 신고-가 가왓타노니 기즈키마센데시타\n"},
                {"\n당신, 괜찮아요? \n\n你没事吗？\n\n니 메이 스 마?\n","\n경찰을 불러 주세요\n\n请帮我叫一下警察\n\n칭 빵 워 쟈오 이샤 징차\n","\n충돌 사고를 당했어요\n\n我的车被撞了\n\n워 더 처 뻬이 좡 러\n ","\n제가 교통사고를 냈어요\n\n我出车祸了\n\n워 추 처훠 러\n","\n죄송해요. 바뀐 신호를 못 봤어요 \n\n对不起，我没看到信号变了\n\n뚜이부치, 워 메이 칸따오 신하오 뻰 러\n"},
                {"\n당신, 괜찮아요? \n\nÇa va?\n\n싸바?\n","\n경찰을 불러 주세요\n\nAppelez la police\n\n아쁠레 라 뽈리쓰\n","\n충돌 사고를 당했어요\n\nJ'ai eu une collision avec ma voiture\n\n제 위 윈 꼴리지옹 아베끄 마 브와뛰흐\n ","\n제가 교통사고를 냈어요\n\nJ'ai provoqué un accident de voiture\n\n제 프호보께 아 낙씨덩 드 브와뛰흐\n","\n죄송해요. 바뀐 신호를 못 봤어요 \n\nJe n'ai pas vu le feu passer au rouge, excusez-moi\n\n즈 네 빠 뷔 르 푸 빠쎄 오 후쥐, 엑스뀌제 므와\n"},
                {"\n당신, 괜찮아요? \n\n¿Está usted bien? \n\n에스따 우스뗃 비엔?\n","\n경찰을 불러 주세요\n\nPor favor, llame a la policía\n\n뽀르 파보르, 야메 아 라 뽈리씨아\n","\n충돌 사고를 당했어요\n\nHe tenido un accidente de choque\n\n에 떼니도 운 악씨덴떼 데 초께\n ","\n제가 교통사고를 냈어요\n\nSoy yo quien causó el accidente de tráfico\n\n쏘이 요 끼엔 까우소 엘 악씨덴떼 데 뜨라피꼬\n","\n죄송해요. 바뀐 신호를 못 봤어요 \n\nPerdóneme, no he visto que han cambiado las señales\n\n뻬르도네메, 노 에 비스또 께 안 깜비아도 라스 세냘레스\n"}
        };

        String[][] itemsH_1 = {
                {"\n***은 어디에 있나요?\n\nWhere is the ***? \n\n웨어 이즈 더 ***?\n","\n이 근처에 ***이 있어요? \n\nIs there a *** nearby? \n\n이즈 데어 어 *** 니어바이?\n","\n가장 빨리 가는 방법은 뭐예요? \n\nWhat is the fastest way to get there? \n\n왓 이즈 더 패스티스트 웨이 투 겟 데어?\n"},
                {"\n***은 어디에 있나요?\n\n***どこですか\n\n***와 도코데스카?\n","\n이 근처에 ***이 있어요? \n\nこの近くに***ありますか\n\n고노 치카쿠니 *** 아리마스카?\n","\n가장 빨리 가는 방법은 뭐예요? \n\nいちばん速く行く方法は何ですか\n\n이치반 하야쿠 이쿠 호-호-와 난데스카?\n"},
                {"\n***은 어디에 있나요?\n\n***在哪儿？\n\n*** 짜이 나얼?\n","\n이 근처에 ***이 있어요? \n\n这儿附近有***吗？\n\n저얼 푸진 여우 *** 마?\n","\n가장 빨리 가는 방법은 뭐예요? \n\n怎么走最快？\n\n쩐머 쩌우 쭈이 콰이?\n"},
                {"\n***은 어디에 있나요?\n\nOù est ***?\n\n우 에 ***?\n","\n이 근처에 ***이 있어요? \n\nY a-t-il un *** près d'ici? \n\n이야띨 앙 *** 프헤 디씨?\n","\n가장 빨리 가는 방법은 뭐예요? \n\nQuel est le moyen le plus rapide pour y arriver? \n\n껠 에 르 므와이앙 르 쁠뤼 하삐드 뿌흐 이 아히베?\n"},
                {"\n***은 어디에 있나요?\n\n\n\n\n","\n이 근처에 ***이 있어요? \n\n\n\n\n","\n가장 빨리 가는 방법은 뭐예요? \n\n¿Cuál es la forma de llegar ahí más rápido ?\n\n꾸알 에스 라 포르마 데 예가르 아이 마스 라삐도?\n"}
        };
        String[][] itemsH_2 = {
                {"\n걸어서 갈 수 있을까요? \n\nCan I get there on foot? \n\n캔 아이 겟 데어 온 풋?\n","\n거기까지 가는 데 얼마나 걸릴까요? \n\nHow long will it take to get there? \n\n하우 롱 윌 잇 테익 투 겟 데어?\n","\n버스 정류장까지 멉니까? \n\nIs the busstop far? \n\n이즈 더 버스 스탑 파?\n"},
                {"\n걸어서 갈 수 있을까요? \n\n歩いて行けますか\n\n아루이테 이케마스카?\n","\n거기까지 가는 데 얼마나 걸릴까요? \n\nそこまで、どのぐらいかかりますか\n\n소코마데 도노구라이 가카리마스카?\n","\n버스 정류장까지 멉니까? \n\nバス停までは遠いですか\n\n바스테-마데와 도-이데스카?\n"},
                {"\n걸어서 갈 수 있을까요? \n\n能走着去吗？\n\n넝 쩌우 저 취 마?\n","\n거기까지 가는 데 얼마나 걸릴까요? \n\n走到那儿得多长时间？\n\n쩌우따오 나얼 데이 뚸 창 스젠?\n","\n버스 정류장까지 멉니까? \n\n公交车站离这儿远吗？\n\n꿍쟈오처잔 리 저얼 위앤 마?\n"},
                {"\n걸어서 갈 수 있을까요? \n\nJe peux y aller à pied? \n\n즈 쁘 이 알레 아 삐에?\n","\n거기까지 가는 데 얼마나 걸릴까요? \n\nCombien de temps faut-il pour y aller? \n\n꽁비앙 드 떵 포-띨 뿌흐 이 알레?\n","\n버스 정류장까지 멉니까? \n\nL'arrêt de bus est-il loin d'ici? \n\n라헤 드 뷔스 에 띨 르왕 디씨?\n"},
                {"\n걸어서 갈 수 있을까요? \n\n¿Podré ir andando? \n\n뽀드레 이르 안단도?\n","\n거기까지 가는 데 얼마나 걸릴까요? \n\n¿Cuánto tardaré para llegar hasta ahí?\n\n꾸안또 따르다레 빠라 예가르 아스따 아이?\n","\n버스 정류장까지 멉니까? \n\n¿Está lejos la parada de autobús? \n\n에스따 레호스 라 빠라다 데 아우또부스?\n"}
        };

        String[][] itemsI_1 = {
                {"\n언제 진료를 받을 수 있을까요?\n\nWhen can I see the doctor?\n\n웬 캔 아이 씨 더 닥터?\n","\n진찰 받으러 왔어요\n\nI've come to receive treatment\n\n아입 컴 투 리씨브 트리트먼트\n","\n배가 아파요\n\nI have a stomachache\n\n아이 햅 어 스터먹에익\n","\n여기가 아파요\n\nIt hurts here\n\n잇 허츠 히어\n"},
                {"\n언제 진료를 받을 수 있을까요?\n\nいつ、診療を受けられますか\n\n이츠 신료-오 우케라레마스카?\n","\n진찰 받으러 왔어요\n\n診療を受けにきましたが\n\n신료-오 우케니 기마시타가\n","\n배가 아파요\n\nおなかが痛いです\n\n오나카가 이타이데스\n","\n여기가 아파요\n\nここが痛いです\n\n고코가 이타이데스\n"},
                {"\n언제 진료를 받을 수 있을까요?\n\n什么时候能看上病？\n\n선머 스허우 넝 칸상 삥?\n","\n진찰 받으러 왔어요\n\n我来看病\n\n워 라이 칸 삥\n","\n배가 아파요\n\n肚子疼\n\n뚜쯔 텅\n","\n여기가 아파요\n\n这儿疼\n\n저얼 텅\n"},
                {"\n언제 진료를 받을 수 있을까요?\n\nQuand serait-il possible de voir le médecin? \n\n껑 쓰헤띨 뽀씨블르 드 부아흐 르 메드쌩?\n","\n진찰 받으러 왔어요\n\nJe suis venu pour consulter un médecin\n\n즈 쒸 브뉘 뿌흐 꽁쓀떼 앵 메드쌩\n","\n배가 아파요\n\nJ'ai mal au ventre\n\n제 말 오 벙트흐\n","\n여기가 아파요\n\nJ'ai mal ici\n\n제 말 이씨\n"},
                {"\n언제 진료를 받을 수 있을까요?\n\n¿Cuándo podría recibir tratamiento? \n\n꾸안도 뽀드리아 레씨비르 뜨라따미엔또?\n","\n진찰 받으러 왔어요\n\nHe venido aquí a recibir tratamiento\n\n에 베니도 아끼 아 레씨비르 뜨라따미엔또\n","\n배가 아파요\n\nMe duele el estómago\n\n메 두엘레 엘 에스또마고\n","\n여기가 아파요\n\nTengo un dolor aquí\n\n뗑고 운 돌로르 아끼\n"}
        };
        String[][] itemsI_2 = {
                {"\n멀미약이 있어요?\n\nDo you have anything for carsickness?\n\n두 유 햅 애니씽 포 카씨크니스?\n","\n진통제/아스피린/살충제 좀 주세요\n\nI would like some (painkiller/aspirin/insect repellent), please\n\n아이 우드 라익 썸 (페인킬러/아스피린/인쎅트 리퍼런트, 플리즈\n","\n하루에 몇 알씩 먹어야 해요? \n\nHow many pills do I have to take a day? \n\n하우 매니 필스 두 아이 햅 투 테익 어 데이?\n"},
                {"\n멀미약이 있어요?\n\n乗り物酔い薬、ありますか\n\n노리모노요이 구스리 아리마스카?\n","\n진통제/아스피린/살충제 좀 주세요\n\n(鎮痛剤/アスピリン/殺虫剤)ください\n\n(친츠-자이/아스피린/삿추자이) 구다사이\n","\n하루에 몇 알씩 먹어야 해요? \n\n1日に何錠ずつ飲むんですか\n\n이치니치니 난조-즈츠 노문데스카?\n"},
                {"\n멀미약이 있어요?\n\n有晕车药吗？\n\n여우 윈처야오 마?\n","\n진통제/아스피린/살충제 좀 주세요\n\n请给我(止痛药/阿司匹林/杀虫剂)\n\n칭 게이 워 (즈퉁야오/아쓰피린/사충지)\n","\n하루에 몇 알씩 먹어야 해요? \n\n一天得吃几片？\n\n이텐 데이 츠 지 펜?\n"},
                {"\n멀미약이 있어요?\n\nAvez-vous des médicaments préventifs pour le mal des transports?\n\n아베 부 데 메디꺄멍 프헤벙티프 뿌흐 르 말 데 트헝스뽀흐?\n","\n진통제/아스피린/살충제 좀 주세요\n\nJe voudrais (un analgésique/des aspirines/Un insecticide)\n\n즈 부드헤 (아나날제지끄/데자스삐힌/아냉섹띠씨드\n","\n하루에 몇 알씩 먹어야 해요? \n\nJ'en prends combien par jour? \n\n정 프헝 꽁비앙 빠흐 쥬흐?\n"},
                {"\n멀미약이 있어요?\n\n¿Tiene algún medicamento contra las náuseas? \n\n띠에네 알군 메디까멘또 꼰뜨라 라스 나우세아스?\n","\n진통제/아스피린/살충제 좀 주세요\n\nDéme un (analgésico/aspirina/insecticida), por favor\n\n데메 운 (아날헤시꼬/아스삐리나/인섹띠씨다), 뽀르 파보르\n","\n하루에 몇 알씩 먹어야 해요? \n\n¿Cuántas pastillas tengo que tomar al día? \n\n꾸안따스 빠스띠야스 뗑고 께 또마르 알 디아?\n"}
        };



        String[][] items1 = {{"a"}};
        if(cursor1.getString(0).equals("A1"))
            items1 = itemsA_1;
        else if (cursor1.getString(0).equals("A2"))
            items1 = itemsA_2;
        else if (cursor1.getString(0).equals("A3"))
            items1 = itemsA_3;
        else if (cursor1.getString(0).equals("A4"))
            items1 = itemsA_4;
        else if (cursor1.getString(0).equals("A5"))
            items1 = itemsA_5;

        else if(cursor1.getString(0).equals("B1"))
            items1 = itemsB_1;
        else if (cursor1.getString(0).equals("B2"))
            items1 = itemsB_2;
        else if (cursor1.getString(0).equals("B3"))
            items1 = itemsB_3;
        else if (cursor1.getString(0).equals("B4"))
            items1 = itemsB_4;

        else if(cursor1.getString(0).equals("C1"))
            items1 = itemsC_1;
        else if (cursor1.getString(0).equals("C2"))
            items1 = itemsC_2;

        else if(cursor1.getString(0).equals("D1"))
            items1 = itemsD_1;
        else if (cursor1.getString(0).equals("D2"))
            items1 = itemsD_2;
        else if (cursor1.getString(0).equals("D3"))
            items1 = itemsD_3;
        else if (cursor1.getString(0).equals("D4"))
            items1 = itemsD_4;
        else if (cursor1.getString(0).equals("D5"))
            items1 = itemsD_5;

        else if(cursor1.getString(0).equals("E1"))
            items1 = itemsE_1;
        else if (cursor1.getString(0).equals("E2"))
            items1 = itemsE_2;
        else if (cursor1.getString(0).equals("E3"))
            items1 = itemsE_3;
        else if (cursor1.getString(0).equals("E4"))
            items1 = itemsE_4;
        else if (cursor1.getString(0).equals("E5"))
            items1=itemsE_5;
        else if (cursor1.getString(0).equals("E6"))
            items1 = itemsE_6;
        else if (cursor1.getString(0).equals("E7"))
            items1 = itemsE_7;


        else if(cursor1.getString(0).equals("F1"))
            items1 = itemsF_1;
        else if (cursor1.getString(0).equals("F2"))
            items1 = itemsF_2;
        else if (cursor1.getString(0).equals("F3"))
            items1 = itemsF_3;

        else if(cursor1.getString(0).equals("G1"))
            items1 = itemsG_1;
        else if (cursor1.getString(0).equals("G2"))
            items1 = itemsG_2;
        else if (cursor1.getString(0).equals("G3"))
            items1 = itemsG_3;
        else if (cursor1.getString(0).equals("G4"))
            items1 = itemsG_4;

        else if(cursor1.getString(0).equals("H1"))
            items1 = itemsH_1;
        else if (cursor1.getString(0).equals("H2"))
            items1 = itemsH_2;

        else if(cursor1.getString(0).equals("I1"))
            items1 = itemsI_1;
        else if (cursor1.getString(0).equals("I2"))
            items1 = itemsI_2;

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items1[0]) ;
        Button button = (Button)findViewById(R.id.ok);
        final ListView e = (ListView) findViewById(R.id.e);
        e.setAdapter(adapter1);

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items1[1]);
        final ListView j = (ListView) findViewById(R.id.j);
        j.setAdapter(adapter2);

        ArrayAdapter adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items1[2]);
        final ListView c = (ListView) findViewById(R.id.c);
        c.setAdapter(adapter3);

        ArrayAdapter adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items1[3]);
        final ListView f = (ListView) findViewById(R.id.f);
        f.setAdapter(adapter4);

        ArrayAdapter adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items1[4]);
        final ListView s = (ListView) findViewById(R.id.s);
        s.setAdapter(adapter5);





        arrayList = new ArrayList();
        arrayList.add("선택하세요");
        arrayList.add("영어");
        arrayList.add("일본어");
        arrayList.add("중국어");
        arrayList.add("프랑스어");
        arrayList.add("스페인어");



        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapterA = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner1.setAdapter(adapterA);



        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = (String) parent.getItemAtPosition(position);
                if (selectedItem.equals("영어")) {

                    e.setVisibility(View.VISIBLE);
                    j.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                    f.setVisibility(View.GONE);
                    s.setVisibility(View.GONE);

                } else if (selectedItem.equals("일본어")) {

                    j.setVisibility(View.VISIBLE);
                    e.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                    f.setVisibility(View.GONE);
                    s.setVisibility(View.GONE);

                } else if (selectedItem.equals("중국어")) {

                    c.setVisibility(View.VISIBLE);
                    j.setVisibility(View.GONE);
                    e.setVisibility(View.GONE);
                    f.setVisibility(View.GONE);
                    s.setVisibility(View.GONE);

                } else if (selectedItem.equals("프랑스어")) {

                    f.setVisibility(View.VISIBLE);
                    j.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                    e.setVisibility(View.GONE);
                    s.setVisibility(View.GONE);

                } else if (selectedItem.equals("스페인어")) {

                    s.setVisibility(View.VISIBLE);
                    j.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                    f.setVisibility(View.GONE);
                    e.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(languageView.this, index.class);
            startActivity(intent);
        }
    });


    }


}
