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
