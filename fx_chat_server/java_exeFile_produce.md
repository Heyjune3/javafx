export한 jar파일 폴더에 들어가 경로에 cmd 명령 프롬프트 실행
java --module-path "C:\Program Files\Java\javafx-sdk-17.0.9\lib" --add-modules=javafx.controls,javafx.fxml -jar chat_server.jar
컴퓨터에 다운받아져 있는 javafx폴더를 해당 폴더에 복사
java --module-path "javafx_17\lib" --add-modules=javafx.controls,javafx.fxml -jar chat_server.jar

https://launch4j.sourceforge.net/
launch4j == jar 파일을 exe 실행 파일로 만들기
- launch4j 실행 후 jar에 jar파일 넣기 - Output file에 위치 넣고 확장자명 .exe (ex)C:\Users\admin\Desktop\ServerProgram\chat_server.exe)
- icon 넣기 (.ico 확장자 파일밖에 안됨)
- farvicon == 파비콘이란 웹사이트 또는 웹 페이지를 대표하기 위해 웹 브라우저에서 사용되는 16x16 픽셀의 작은 이미지입니다.
- 파비콘제작사이트 검색 - 제작 후 icon 에 넣기

Error title : 에러 발생 시 자바 다운로드 지원힌트
- java Download Site 입력 - Java download URL : https://oracle.com 입력

Classpath - Main class 에 jar 파일 넣기 - Accept 클릭

JRE - JRE paths에 자바가 깔려있는 폴더 경로 복사붙여넣기(C:\Program Files\Java\jdk-17)
- Min JRE version : 자바 버전번호

JVM options : --module-path "javafx_17\lib" --add-modules=javafx.controls,javafx.fxml 옵션 넣기

설정 이후 위쪽 상단 톱니바퀴모양 클릭 - 경로 지정 후 파일 이름 적고 저장 - 완료

사용자가 exe파일 실행 시 자바가 다운로드 받아져 있지 않을 경우
- 자바 다운로드 폴더를 그대로 해당 폴더에 붙여넣기 - JRE paths에 java 폴더명(jdk-17)만 넣기 (해당 폴더에 붙여넣어서 현재경로로 사용 가능)