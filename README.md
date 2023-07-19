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
  
