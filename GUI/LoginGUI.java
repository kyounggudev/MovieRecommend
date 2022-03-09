package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.UserBtnGUI;
import Movie.Movie;
import Movie.MovieBook;
import People.Admin;
import People.People;
import People.User;
import Review.Review;
import Review.ReviewBook;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/* 시작 - 로그인 화면 */

public class LoginGUI extends JFrame implements ActionListener {

	private JLabel idLabel = new JLabel("아이디 ");				// 아이디
	private JTextField idField = new JTextField(19);			// 아이디 입력 필드
	private JLabel pwLabel = new JLabel("비밀번호 ");				// 비밀번호
	private JPasswordField pwField = new JPasswordField(19);	// 비밀번호 입력 필드
	private JButton logBtn = new JButton("Login");				// 로그인 버튼
	private ArrayList<User> users;
	private ArrayList<Admin> admins;
	private int result=0;
	private MovieBook mb;
	private ReviewBook rb;
	public static User user=null;
	public static Admin admin=null;

	public static void main(String[] args) {
		MovieBook mb = new MovieBook();
		ReviewBook rb = new ReviewBook();
		ArrayList actors1 = new ArrayList();
		ArrayList actors2 = new ArrayList();
		actors1.add("마동석");
		actors1.add("윤계상");
		actors2.add("공유");
		actors2.add("정유미");
		actors2.add("마동석");
		actors2.add("김수안");
		Movie m1 = new Movie("범죄도시", "강윤성", actors1, "2017-19-03"
				, "액션", "2004년 서울… 하얼빈에서 넘어와 단숨에 기존 조직들을 장악하고 가장 강력한 세력인 춘식이파 보스 ‘황사장(조재윤 분)’까지 위협하며 도시 일대의 최강자로 급부상한 신흥범죄조직의 악랄한 보스 ‘장첸(윤계상 분)’. 대한민국을 뒤흔든 ‘장첸(윤계상 분)’ 일당을 잡기 위해 오직 주먹 한방으로 도시의 평화를 유지해 온 괴물형사 ‘마석도(마동석 분)’와 인간미 넘치는 든든한 리더 ‘전일만(최귀화 분)’ 반장이 이끄는 강력반은 나쁜 놈들을 한방에 쓸어버릴 끝.짱.나.는. 작전을 세우는데… 통쾌하고! 화끈하고! 살벌하게! 나쁜 놈들 때려잡는 강력반 형사들의 ‘조폭소탕작전’이 시작된다!", 6800000);
		Movie m2 = new Movie("부산행", "연상호", actors2, "2016-07-20"
				, "액션", "정체불명의 바이러스가 전국으로 확산되고 대한민국 긴급재난경보령이 선포된 가운데, 열차에 몸을 실은 사람들은 단 하나의 안전한 도시 부산까지 살아가기 위한 치열한 사투를 벌이게 된다. 서울에서 부산까지의 거리 442KM 지키고 싶은, 지켜야만 하는 사람들의 극한의 사투!", 11570000);
		mb.addMovie("1", m1);
		mb.addMovie("2", m2);
		ArrayList actors3 = new ArrayList();
		actors3.add("이병헌");
		actors3.add("하정우");
		actors3.add("마동석");
		actors3.add("전혜진");
		actors3.add("수지");
		Movie m3 = new Movie("백두산", "이해준", actors3, "2019-12-19"
				,"드라마", "대한민국 관측 역사상 최대 규모의 백두산 폭발 발생. 갑작스러운 재난에 한반도는 순식간에 아비규환이 되고, 남과 북 모두를 집어삼킬 추가 폭발이 예측된다. 사상 초유의 재난을 막기 위해 ‘전유경’(전혜진)은 백두산 폭발을 연구해 온 지질학 교수 ‘강봉래’(마동석)의 이론에 따른 작전을 계획하고, 전역을 앞둔 특전사 EOD 대위 ‘조인창’(하정우)이 남과 북의 운명이 걸린 비밀 작전에 투입된다. 작전의 키를 쥔 북한 무력부 소속 일급 자원 ‘리준평’(이병헌)과 접선에 성공한 ‘인창’. 하지만 ‘준평’은 속을 알 수 없는 행동으로 ‘인창’을 곤란하게 만든다. 한편, ‘인창’이 북한에서 펼쳐지는 작전에 투입된 사실도 모른 채 서울에 홀로 남은 ‘최지영’(배수지)은 재난에 맞서 살아남기 위해 고군분투하고 그 사이, 백두산 마지막 폭발까지의 시간은 점점 가까워 가는데…!",8250000);
		mb.addMovie("3", m3);
		
		ArrayList actors4 = new ArrayList();
		actors4.add("류승룡");
		actors4.add("이하늬");
		actors4.add("진선규");
		actors4.add("이동휘");
		actors4.add("공명");
		Movie m4 = new Movie("극한직업", "이병현", actors4, "2019-01-23"
				,"코미디", "불철주야 달리고 구르지만 실적은 바닥, 급기야 해체 위기를 맞는 마약반! 더 이상 물러설 곳이 없는 팀의 맏형 고반장은 국제 범죄조직의 국내 마약 밀반입 정황을 포착하고 장형사, 마형사, 영호, 재훈까지 4명의 팀원들과 함께 잠복 수사에 나선다. 마약반은 24시간 감시를 위해 범죄조직의 아지트 앞 치킨집을 인수해 위장 창업을 하게 되고, 뜻밖의 절대미각을 지닌 마형사의 숨은 재능으로 치킨집은 일약 맛집으로 입소문이 나기 시작한다. 수사는 뒷전, 치킨장사로 눈코 뜰 새 없이 바빠진 마약반에게 어느 날 절호의 기회가 찾아오는데… 범인을 잡을 것인가, 닭을 잡을 것인가!",16260000);
		mb.addMovie("4", m4);

		ArrayList actors5 = new ArrayList();
		actors5.add("조정석");
		actors5.add("윤아");
		Movie m5 = new Movie("엑시트", "이상근", actors5, "2019-07-31"
				,"액션", "대학교 산악 동아리 에이스 출신이지만 졸업 후 몇 년째 취업 실패로 눈칫밥만 먹는 용남은 온 가족이 참석한 어머니의 칠순 잔치에서 연회장 직원으로 취업한 동아리 후배 의주를 만난다 어색한 재회도 잠시, 칠순 잔치가 무르익던 중 의문의 연기가 빌딩에서 피어 오르며 피할 새도 없이 순식간에 도심 전체는 유독가스로 뒤덮여 일대혼란에 휩싸이게 된다. 용남과 의주는 산악 동아리 시절 쌓아 뒀던 모든 체력과 스킬을 동원해 탈출을 향한 기지를 발휘하기 시작하는데…",9420000);
		mb.addMovie("5", m5);
		
		ArrayList actors6 = new ArrayList();
		actors6.add("하정우");
		actors6.add("차태현");
		actors6.add("주지훈");
		actors6.add("김향기");
		actors6.add("김동욱");
		Movie m6 = new Movie("신과함께-죄와 벌", "김용화", actors6, "2017-12-20"
				,"판타지", "저승 법에 의하면, 모든 인간은 사후 49일 동안 7번의 재판을 거쳐야만 한다. 살인, 나태, 거짓, 불의, 배신, 폭력, 천륜 7개의 지옥에서 7번의 재판을 무사히 통과한 망자만이 환생하여 새로운 삶을 시작할 수 있다. “김자홍 씨께선, 오늘 예정 대로 무사히 사망하셨습니다” 화재 사고 현장에서 여자아이를 구하고 죽음을 맞이한 소방관 자홍, 그의 앞에 저승차사 해원맥과 덕춘이 나타난다. 자신의 죽음이 아직 믿기지도 않는데 덕춘은 정의로운 망자이자 귀인이라며 그를 치켜세운다. 저승으로 가는 입구, 초군문에서 그를 기다리는 또 한 명의 차사 강림, 그는 차사들의 리더이자 앞으로 자홍이 겪어야 할 7개의 재판에서 변호를 맡아줄 변호사이기도 하다. 염라대왕에게 천년 동안 49명의 망자를 환생시키면 자신들 역시 인간으로 환생시켜 주겠다는 약속을 받은 삼차사들, 그들은 자신들이 변호하고 호위해야 하는 48번째 망자이자 19년 만에 나타난 의로운 귀인 자홍의 환생을 확신하지만, 각 지옥에서 자홍의 과거가 하나 둘씩 드러나면서 예상치 못한 고난과 맞닥뜨리는데… 누구나 가지만 아무도 본 적 없는 곳, 새로운 세계의 문이 열린다!",14420000);
		mb.addMovie("6", m6);
		
		ArrayList actors7 = new ArrayList();
		actors7.add("송강호");
		actors7.add("변희봉");
		actors7.add("박해일");
		actors7.add("배두나");
		actors7.add("고아성");
		Movie m7 = new Movie("괴물", "봉준호", actors7, "2006-07-27"
				,"모험", "햇살 가득한 평화로운 한강 둔치 아버지(변희봉)가 운영하는 한강 매점, 늘어지게 낮잠 자던 강두(송강호)는 잠결에 들리는 ‘아빠’라는 소리에 벌떡 일어난다. 올해 중학생이 된 딸 현서(고아성)가 잔뜩 화가 나있다. 꺼내놓기도 창피한 오래된 핸드폰과, 학부모 참관 수업에 술 냄새 풍기며 온 삼촌(박해일)때문이다. 강두는 고민 끝에 비밀리에 모아 온 동전이 가득 담긴 컵라면 그릇을 꺼내 보인다. 그러나 현서는 시큰둥할 뿐, 막 시작된 고모(배두나)의 전국체전 양궁경기에 몰두해 버린다. 그곳에서 괴물이 나타났다. 한강 둔치로 오징어 배달을 나간 강두, 우연히 웅성웅성 모여있는 사람들 속에서 특이한 광경을 목격하게 된다. 생전 보도 못한 무언가가 한강다리에 매달려 움직이는 것이다. 사람들은 마냥 신기해하며 핸드폰, 디카로 정신 없이 찍어댄다. 그러나 그것도 잠시… 정체를 알 수 없는 괴물은 둔치 위로 올라와 사람들을 거침없이 깔아뭉개고, 무차별로 물어뜯기 시작한다. 순식간에 아수라장으로 돌변하는 한강변. 강두도 뒤늦게 딸 현서를 데리고 정신 없이 도망가지만, 비명을 지르며 흩어지는 사람들 속에서, 꼭 잡았던 현서의 손을 놓치고 만다. 그 순간 괴물은 기다렸다는 듯이 현서를 낚아채 유유히 한강으로 사라진다. 어딘가에 있을 현서를 반드시 찾아야 한다. 갑작스런 괴물의 출현으로 한강은 모두 폐쇄되고, 도시 전체는 마비된다. 하루아침에 집과 생계, 그리고 가장 소중한 현서까지 모든 것을 잃게 된 강두 가족… 돈도 없고 빽도 없는 그들은 아무도 도와주지 않지만, 위험구역으로 선포된 한강 어딘가에 있을 현서를 찾아 나선다.",10910000);
		mb.addMovie("7", m7);
		
		ArrayList actors8 = new ArrayList();
		actors8.add("송강호");
		actors8.add("이선균");
		actors8.add("조여정");
		actors8.add("최우식");
		actors8.add("박소담");
		Movie m8 = new Movie("기생충", "봉준호", actors8, "2019-05-30"
				,"드라마", "전원백수로 살 길 막막하지만 사이는 좋은 기택(송강호) 가족. 장남 기우(최우식)에게 명문대생 친구가 연결시켜 준 고액 과외 자리는 모처럼 싹튼 고정수입의 희망이다. 온 가족의 도움과 기대 속에 박사장(이선균) 집으로 향하는 기우. 글로벌 IT기업 CEO인 박사장의 저택에 도착하자 젊고 아름다운 사모님 연교(조여정)가 기우를 맞이한다. 그러나 이렇게 시작된 두 가족의 만남 뒤로, 걷잡을 수 없는 사건이 기다리고 있었으니…",10310000);
		mb.addMovie("8", m8);
		
		ArrayList actors9 = new ArrayList();
		actors9.add("크리스 에반스");
		actors9.add("송강호");
		actors9.add("에드 해리스");
		actors9.add("존 허트");
		actors9.add("고아성");
		Movie m9 = new Movie("설국열차", "봉준호", actors9, "2013-08-01"
				,"SF", "기상 이변으로 모든 것이 꽁꽁 얼어붙은 지구. 살아남은 사람들을 태운 기차 한 대가 끝없이 궤도를 달리고 있다. 춥고 배고픈 사람들이 바글대는 빈민굴 같은 맨 뒤쪽의 꼬리칸, 그리고 선택된 사람들이 술과 마약까지 즐기며 호화로운 객실을 뒹굴고 있는 앞쪽칸. 열차 안의 세상은 결코 평등하지 않다. 기차가 달리기 시작한 17년 째, 꼬리칸의 젊은 지도자 커티스는 긴 세월 준비해 온 폭동을 일으킨다. 기차의 심장인 엔진을 장악, 꼬리칸을 해방시키고 마침내 기차 전체를 해방 시키기 위해 절대권력자 윌포드가 도사리고 있는 맨 앞쪽 엔진칸을 향해 질주하는 커티스와 꼬리칸 사람들. 그들 앞에 예기치 못한 상황들이 기다리고 있는데…",9350000);
		mb.addMovie("9", m9);
		
		ArrayList actors10 = new ArrayList();
		actors10.add("조승우");
		actors10.add("김혜수");
		actors10.add("백윤식");
		actors10.add("유해진");
		actors10.add("김윤석");
		Movie m10 = new Movie("타짜", "최동훈", actors10, "2006-09-28"
				,"범죄", "가구공장에서 일하며 남루한 삶을 사는 고니는 대학보다 가난을 벗어나게 해줄 돈이 우선인 열혈 천방지축 청년! 어느 날 고니는, 가구공장 한 켠에서 박무석 일행이 벌이는 화투판에 끼게 된다. 스무장의 화투로 벌이는 '섯다' 한 판! 하지만 고니는 그 판에서 삼년 동안 모아두었던 돈 전부를 날리고 만다. 그것이 전문도박꾼 타짜들이 짜고 친 판이었단 사실을 뒤늦게 안 고니는 박무석 일행을 찾아 나서고, 도박으로 시비가 붙은 한 창고에서 우연인 듯 필연처럼 전설의 타짜 평경장을 만난다. 그리고 잃었던 돈의 다섯 배를 따면 화투를 그만두겠단 약속을 하고, 그와 함께 본격적인 꽃싸움에 몸을 던지기 위한 동행길에 오른다. 드디어 타짜의 길로 들어선 고니! 평경장과 지방원정을 돌던 중 도박판의 꽃, 설계자 정마담을 소개 받고 둘은 서로에게서 범상치 않은 승부욕과 욕망의 기운을 느끼게 된다. 고니는 정마담이 미리 설계해 둔 판에서 큰 돈을 따게 되고, 결국 커져 가는 욕망을 이기지 못한 채 평경장과의 약속을 어기고 만다. 정마담과의 화려한 도박인생, 평경장과의 헤어짐을 택한 고니. 유유자적 기차에 오르는 평경장과 마지막 인사를 나눈 고니는 그 기차역에서 극악무도한 독종이자 죽음의 타짜란 아귀를 스치듯 만난다. 이후 고니는, 정마담의 술집에서 벌어진 한 화투판에서 요란스러운 입담으로 판을 흔드는 고광렬을 만나고, 경찰의 단속을 피하던 중 그와 함께 정마담을 떠나게 된다. 고광렬은 고니와는 달리 남들 버는 만큼만 따면 된다는 직장인 마인드의 인간미 넘치는 타짜! 둘은 환상의 호흡을 자랑하며 전국의 화투판을 휩쓴다. 함께 원정을 뛰며 나름의 도박인생을 꾸려가는 고니와 고광렬. 원정 중 우연히 들린 한 술집에서 고니는 술집주인 화란을 만나고 둘은 첫눈에 서로에게 끌리지만 한없이 떠도는 타짜의 인생에 사랑은 그리 쉬운 일이 아니다. 한편, 고니는 자신을 이 세계에 발 담그게 한 장본인 박무석과 그를 조종하는 인물 곽철용을 찾게 되고, 드디어 보기 좋게 한 판 복수에 성공한다. 하지만 곽철용의 수하는 복수가 낳은 복수를 위해 아귀에게 도움을 청하고, 아귀는 고니에게 애증을 가진 정마담을 미끼로 고니와 고광렬을 화투판으로 끌어들인다. 기차역에서 스쳤던 아귀를 기억해내며 그것이 '죽음의 한 판'이란 것을 느끼는 고니. 하지만 고니는 이를 거절하지 않는다. 고광렬의 만류도 뿌리친 채, 그리고 처음으로 평범한 삶을 꿈꾸게 한 여자 화란과의 사랑도 뒤로 한 채, 고니는 그렇게 죽음의 판이 펼쳐질 배에 스스로 오르는데.... 물러설 곳 없는 꽃들의 전쟁..! 각자의 원한과 욕망, 그리고 덧없는 희망, 이 모든 것이 뒤엉킨 한 판이 시작된다..! \"겁날 것도, 억울할 것도 없다. 내가 아는 모든 사람이 그랬던 것처럼, 언젠가는 나도 다치거나 죽는다. 그게 타짜이니까...",5680000);
		mb.addMovie("10", m10);
		
		ArrayList actors11 = new ArrayList();
		actors11.add("유해진");
		actors11.add("조진웅");
		actors11.add("이서진");
		actors11.add("염정아");
		actors11.add("김지수");
		Movie m11 = new Movie("완벽한 타인", "이재규", actors11, "2018-10-31"
				,"드라마", "우리 게임 한 번 해볼까? 다들 핸드폰 올려봐 저녁 먹는 동안 오는 모든 걸 공유하는 거야 전화, 문자, 카톡, 이메일 할 것 없이 싹! 오랜만의 커플 모임에서 한 명이 게임을 제안한다. 바로 각자의 핸드폰을 테이블 위에 올려두고 통화 내용부터 문자와 이메일까지 모두 공유하자고 한 것. 흔쾌히 게임을 시작하게 된 이들의 비밀이 핸드폰을 통해 들통나면서 처음 게임을 제안했던 것과는 전혀 다른 상상치 못한 결말로 흘러가는데…. 상상한 모든 예측이 빗나간다!",5290000);
		mb.addMovie("11", m11);
		
		ArrayList actors12 = new ArrayList();
		actors12.add("최민식");
		actors12.add("하정우");
		actors12.add("조진웅");
		actors12.add("마동석");
		actors12.add("곽도원");
		Movie m12 = new Movie("범죄와의 전쟁: 나쁜놈들 전성시대", "윤종빈", actors12, "2012-02-02"
				,"범죄", "비리 세관 공무원 최익현, 보스 최형배를 만나다! 1982년 부산. 해고될 위기에 처한 비리 세관원 최익현(최민식)은 순찰 중 적발한 히로뽕을 일본으로 밀수출, 마지막으로 한 탕 하기 위해 부산 최대 조직의 젊은 보스 최형배(하정우)와 손을 잡는다. 머리 쓰는 나쁜 놈과 주먹 쓰는 나쁜 놈, 부산을 접수하다! 익현은 탁월한 임기응변과 특유의 친화력으로 형배의 신뢰를 얻는 데 성공한다. 주먹 넘버원 형배와 로비의 신 익현은 함께 힘을 합쳐 부산을 접수하기 시작하고, 두 남자 앞에 나쁜 놈들의 전성시대가 펼쳐진다. 넘버원이 되고 싶은 나쁜 놈들의 한판 승부. 범죄와의 전쟁 하지만 1990년 범죄와의 전쟁이 선포되자 조직의 의리는 금이 가고 넘버원이 되고 싶은 나쁜 놈들 사이의 배신이 시작된다. 살아남기 위해 벌이는 치열한 한판 승부, 최후에 웃는 자는 과연 누가 될 것인가?",4720000);
		mb.addMovie("12", m12);
		
		User u1 = new User("id1", "1234", "name1", 23);
		User u2 = new User("id2", "1234", "name2", 25);
		User u3 = new User("id3", "1234", "name3", 25);
		User u4 = new User("id4", "1234", "name4", 25);
		User u5 = new User("id5", "1234", "name5", 25);
		
		Admin a1 = new Admin("aid1", "a1234", "aname1", 1);
		Admin a2 = new Admin("aid2", "a1234", "aname2", 2);

		ArrayList<People> accounts = new ArrayList<People>();
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<Admin> admins = new ArrayList<Admin>();
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		users.add(u5);
		
		admins.add(a1);
		admins.add(a2);
		accounts.add(u1);
		accounts.add(u2);
		accounts.add(u3);
		accounts.add(u4);
		accounts.add(u5);
		accounts.add(a1);
		accounts.add(a2);

		Review r1 = new Review(u1, m1, 3.0, "리뷰 내용1", "2020-11-10");
		Review r2 = new Review(u2, m1, 5.0, "리뷰 내용2", "2020-10-10");
		Review r3 = new Review(u3, m2, 1.0, "리뷰 내용3", "2020-11-15");
		Review r4 = new Review(u4, m2, 3.0, "리뷰 내용4", "2020-12-10");
		Review r5 = new Review(u5, m3, 5.0, "리뷰 내용5", "2020-11-22");
		Review r6 = new Review(u1, m3, 1.0, "리뷰 내용6", "2020-12-24");
		Review r7 = new Review(u2, m4, 4.0, "리뷰 내용7", "2020-11-24");
		Review r8 = new Review(u3, m4, 3.0, "리뷰 내용8", "2020-12-30");
		Review r9 = new Review(u4, m5, 2.0, "리뷰 내용9", "2020-11-11");
		Review r10 = new Review(u5, m5, 5.0, "리뷰 내용10", "2020-12-09");
		Review r11 = new Review(u1, m6, 1.0, "리뷰 내용11", "2020-09-02");
		Review r12 = new Review(u2, m6, 2.0, "리뷰 내용12", "2020-10-01");
		Review r13 = new Review(u1, m7, 3.0, "리뷰 내용13", "2020-10-02");
		Review r14 = new Review(u1, m7, 4.0, "리뷰 내용14", "2020-10-03");
		Review r15 = new Review(u1, m8, 5.0, "리뷰 내용15", "2020-10-04");
		Review r16 = new Review(u1, m8, 5.0, "리뷰 내용16", "2020-10-05");
		Review r17 = new Review(u1, m9, 4.0, "리뷰 내용17", "2020-10-06");
		Review r18 = new Review(u1, m9, 3.0, "리뷰 내용18", "2020-10-07");
		Review r19 = new Review(u1, m10, 2.0, "리뷰 내용19", "2020-10-08");
		Review r20 = new Review(u3, m10, 1.0, "리뷰 내용20", "2020-10-09");
		Review r21 = new Review(u4, m11, 2.0, "리뷰 내용21", "2020-10-10");
		Review r22 = new Review(u5, m11, 3.0, "리뷰 내용22", "2020-11-05");
		Review r23 = new Review(u5, m12, 4.0, "리뷰 내용23", "2020-12-05");
		Review r24 = new Review(u4, m12, 5.0, "리뷰 내용24", "2020-01-05");
		
		rb.addReview(r1); rb.addReview(r2); rb.addReview(r3); rb.addReview(r4);rb.addReview(r5);rb.addReview(r6);rb.addReview(r7);rb.addReview(r8);rb.addReview(r9);rb.addReview(r10);
		rb.addReview(r11);rb.addReview(r12);rb.addReview(r13);rb.addReview(r14);rb.addReview(r15);rb.addReview(r16);rb.addReview(r17);rb.addReview(r18);rb.addReview(r19);rb.addReview(r20);
		rb.addReview(r21);rb.addReview(r22);rb.addReview(r23);rb.addReview(r24);
		m1.average=rb.getAverage(mb, m1);
		m2.average=rb.getAverage(mb, m2);
		m3.average=rb.getAverage(mb, m3);
		m4.average=rb.getAverage(mb, m4);
		m5.average=rb.getAverage(mb, m5);
		m6.average=rb.getAverage(mb, m6);
		m7.average=rb.getAverage(mb, m7);
		m8.average=rb.getAverage(mb, m8);
		m9.average=rb.getAverage(mb, m9);
		m10.average=rb.getAverage(mb, m10);
		m11.average=rb.getAverage(mb, m11);
		m12.average=rb.getAverage(mb, m12);
		
		m1.reviewCount=rb.countReview(mb, m1);
		m2.reviewCount=rb.countReview(mb, m2);
		m3.reviewCount=rb.countReview(mb, m3);
		m4.reviewCount=rb.countReview(mb, m4);
		m5.reviewCount=rb.countReview(mb, m5);
		m6.reviewCount=rb.countReview(mb, m6);
		m7.reviewCount=rb.countReview(mb, m7);
		m8.reviewCount=rb.countReview(mb, m8);
		m9.reviewCount=rb.countReview(mb, m9);
		m10.reviewCount=rb.countReview(mb, m10);
		m11.reviewCount=rb.countReview(mb, m11);
		m12.reviewCount=rb.countReview(mb, m12);
		//초기값 입력 끝
		int result =0;
		LoginGUI a = new LoginGUI(users, admins,mb,rb);


	}

	public LoginGUI(ArrayList<User> users, ArrayList<Admin> admins, MovieBook mb, ReviewBook rb) {
		this.users=users;
		this.admins=admins;
		this.mb = mb;
		this.rb = rb;
		setTitle("영화 리뷰 & 추천");		// 제목
		setVisible(true);				// 윈도우를 화면에 표시
		setSize(310,200);				// 크기
		setLocationRelativeTo(null);	// 윈도우가 화면 중앙에서 열림
		setResizable(false);			// 윈도우 크기 고정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// 종료
		logBtn.addActionListener(this);				// 버튼 기능 배정
		createWindow();				// 컴포넌트를 윈도우에 배치
	}

	public User getUser() {
		return user;
	}
	public Admin getAdmin() {
		return admin;
	}
	private void createWindow() {
		JPanel panel = new JPanel(new BorderLayout(0,10));
		panel.setBorder(new EmptyBorder(20,10,10,30));
		panel.add(createCenter());
		add(panel);
	}

	private JPanel createCenter() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(idLabel);		// 아이디
		panel.add(idField);		// 아이디 입력
		panel.add(pwLabel);		// 비밀번호
		panel.add(pwField);		// 비밀번호 입력
		panel.add(logBtn);			// 로그인 버튼
		return panel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		for(User log : users) {
			if(log.id.equals(idField.getText()) && log.pw.equals(pwField.getText())) {
				JOptionPane.showMessageDialog( null, "로그인에 성공했습니다." );
				setVisible(false);				// 새로운 창을 열면서 동시에 로그인 창을 닫음
				user=log;
				result=1;
				new UserBtnGUI(user,mb,rb);
				break;
			}
		}
		for(Admin log : admins) {
			if(log.id.equals(idField.getText()) && log.pw.equals(pwField.getText())) {
				JOptionPane.showMessageDialog( null, "관리자 로그인에 성공했습니다." );
				setVisible(false);				// 새로운 창을 열면서 동시에 로그인 창을 닫음
				admin=log;
				result=2;
				new AdminBtnGUI(admin,mb,rb);
				break;
			}
		}
		if(result==0){
			JOptionPane.showMessageDialog( null , "아이디 또는 비밀번호가 틀립니다. 다시 로그인 해주세요.");
			/* 로그인 실패시 텍스트필드에 입력했던 내용을 삭제 */
			idField.setText(null);	// 필드에 입력한 아이디 삭제
			pwField.setText(null);	// 필드에 입력한 패스워드 삭제
			result=3;
		}
	}
}



