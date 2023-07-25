# android-slide
Android 학습 프로젝트 #2

### 슬라이드 Model 만들기

1. Slide 인터페이스
   1) id : String
   2) size : Size => weight와 height가 멤버인 클래스
   3) color : RGBColor => R, G, B, alpha 멤버인 클래스
   4) type : SlideType => 슬라이드 타입의 enum 클래스
2. RectSlide 클래스 : Slide를 상속받은 사각형 Slide 클래스
3. SlideFactory 추상 클래스
   1) createSlideUuid : Slide의 id에 사용될 9자리 랜덤 문자를 만드는 메서드
   2) createSlide : Slide의 속성값을 받아 Slide 객체를 생성하는 메서드
4. layout-w600dp : 태블릿 화면을 위해 600px이상의 화면에 적용할 layout 폴더 생성

#### 실행화면
<img width="1158" alt="스크린샷 2023-07-17 오후 2 50 44" src="https://github.com/SeungWoo-Ahn/android-slide/assets/78468001/f0fdf137-37a5-425f-9dea-a8160a75b4d6">


### 속성 변경 동작

1. SlideManager 구현
   1) 슬라이드의 선택 상태 변경
   2) 슬라이드 색 랜덤 변경
   3) 슬라이드 투명도 증가/감소
2. 테스트코드 작성
   1) 1-1)의 선택 -> 미선택, 미선택 -> 선택 테스트
   2) 1-2)의 기존 색상과 변경 색상 다른지 테스트
   3) 1-3)의 제한된 범위 내에서 투명도 변경되는지 테스트
3. DataBinding
   1) activity-ktx을 통해 MainActivity에서 viewModels()로 뷰모델 생성
   2) variable의 data로 3-1)의 뷰모델 연결
   3) lifeCycleOwner는 MainActivity로 설정
4. ViewModel
   1) StateFlow를 이용한 상태관리
   2) 현재 슬라이드, 배경색, 투명도, 선택 여부를 각각 저장
   3) DataBinding과 BindingAdapter를 활용해 UI 갱신

#### 실행화면
<img width="569" alt="스크린샷 2023-07-18 오후 5 39 47" src="https://github.com/SeungWoo-Ahn/android-slide/assets/78468001/3be7846f-55ea-4be9-b2c3-a661d5295d3e">


### Unidirectional Data Flow

1. StateFlow -> LiveData로 변경
   1) Slide 객체를 홀드하고 있던 StateFlow를 LiveData로 변경해보았다
   2) StateFlow는 변경되는 상태값을 모두 분리하여 MutableStateFlow로 홀드하였다
   3) LiveData는 홀드하고 있던 객체의 속성이 변경되면 감지되어 상태값을 분리하지 않고 사용해보았다
  

### 슬라이드 목록 표시

1. RecyclerView와 슬라이드 생성 버튼 추가
2. StateFlow가 담고 있던 객체 null 허용
   1) 첫 실행했을 때, StateFlow는 null을 담아 화면에 표시하지 않음
3. SlideAdapter
   1) 생성 버튼 클릭시, Slide가 추가되고 화면 갱신
   2) 슬라이드 아이템을 누를때, 해당 슬라이드 속성으로 화면 갱신
   3) 특정 슬라이드에서 속성을 변경하면, 목록에 해당 슬라이드 아이템 화면 갱신
4. RecyclerView 드래그 구현
   1) ItemHelperListener와 ItemHelperCallback으로 아이템 드래그 구현
   2) 드래그된 후 목록 index값 갱신
  
#### 실행화면
<img width="570" alt="스크린샷 2023-07-19 오후 2 38 52" src="https://github.com/SeungWoo-Ahn/android-slide/assets/78468001/4f080be3-433a-4ee0-b6b7-1925b02d6c81">


### 사진 슬라이드 추가하기

1. 슬라이드 목록
   1) 슬라이드 타입(enum)을 Random을 이용하여 생성한다
   2) 목록 아이템을 길게 누를 시 Popup Menu를 띄운다
   3) Popup Menu 중 하나를 선택 시, Slide의 위치를 이동시킨다
   4) 목록의 position을 계산하여 notifyItemChanged와 notifyItemRangeChanged로 화면을 갱신하다
2. 사진 슬라이드
   1) constraintedHeight/Width, constriantVertical_bias를 이용해 이미지 슬라이드 위치와 크기 제한을 준다
   2) selector(xml)을 이용해 슬라이드 선택 여부를 표시한다
   3) CustomTarget<Bitmap>을 이용하여 이미지 투명도를 조절한다
3. 사진 불러오기
   1) manifest에 외부 파일 접근 권한을 추가한다
   2) byteArray를 갖는 ImageSource 객체를 추가한다
   3) ImageManager로 갤러리에서 가져온 이미지 Uri를 byteArray로 변형시킨다
   4) resultLauncher로 이미지를 가져오면 화면과 목록을 갱신한다
  
#### 실행화면
<img width="537" alt="스크린샷 2023-07-24 오전 11 02 14" src="https://github.com/SeungWoo-Ahn/android-slide/assets/78468001/f525134a-bab9-411e-9881-0eae289bd510">


### 슬라이드 불러오기

1. Retrofit
   1) RetrofitClient를 Object로 만들었다-> OkHttpClient와 Retrofit객체를 최초 생성후 다시 사용하기 위해
   2) 네트워크 통신 interface SlideApi를 만들고, 사용할 메서드를 suspend fun으로 선언하였다-> 코루틴 내부에서 실행하기 위해
   3) 네트워크 통신용 RemoteSlide 객체를 만들었고, 로컬에서 사용하는 Slide 객체로 변환하는 메서드를 선언했다
2. Repository
   1) 로컬, 네트워크 통신용 localDataSource와 remoteDataSource를 만들었다-> Room을 사용하지 않으므로 localDataSource는 LocalDB(슬라이드 리스트)를 캡슐화한다
   2) SlideRepository에 필요한 메서드를 선언하고 해당 구현체에 dataSource를 포함시켜 구현했다-> 여기서 랜덤으로 서버 데이터를 호출한다
   3) ViewModel에서 SlideRepository 구현체를 생성하여 사용한다
3. 이미지 변환
   1) ImageManger에 suspend fun으로 url을 byteArray로 변환하는 메서드를 작성했다
   2) 해당 메서드는 ViewModel에서 네트워크 통신을 하는 코루틴 블록 안에서 실행된다
  
#### 실행화면
<img width="533" alt="스크린샷 2023-07-25 오후 1 26 22" src="https://github.com/SeungWoo-Ahn/android-slide/assets/78468001/e56740f9-5de5-461a-8e33-f7384f0eb7d2">

