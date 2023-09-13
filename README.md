## hello-batch

### [스프링 배치 입문] 예제로 배우는 핵심 Spring Batch
*참고: https://www.inflearn.com/course/예제로-배우는-핵심-스프링-배치/dashboard*  
- 해당 강의를 통해 스프링 배치에 입문합니다

### 다루는 내용
#### 1. 초기 셋업
- 배치 프로젝트 셋업
- docker-compose를 통한 mysql 로컬 실행
- 배치 잡으로 "helloWorld"를 출력해보기
- Job, Step, Tasklet을 통해 하나의 잡을 구성하는 요소 살펴보기

#### 2. JobParameter 검증
- Job 파라미터로 들어오는 값에 대해 검증을 할 수 있음
- Tasklet에서 해당 값을 받아와 Tasklet 코드레벨에서 처리 가능
- Job에서 incrementer()를 통해 Job단에서 검증할 수 있음

#### 3. JobListener
- Job 리스너를 통해, 해당 Job을 수행할 때 전/후에 일관적인 처리를 넣어줄 수 있도록 함
- Job의 status를 통해 해당 Job이 실패한 경우 장애 리포트 조치 등의 로직을 넣어줄 수 있음

#### 4. DB 데이터 이관하기
- Entity + JpaRepository를 통해 A 테이블의 정보를 B 테이블로 옮길 수 있다
- Job이 Step을 구동시키는데, Step을 하기의 단계들로 차례차례 구동시킬 수 있다
  - ItemReader: A 테이블에서 정보를 읽어오기
  - ItemProcessor: A 테이블의 정보를 B 테이블에 넣을 수 있도록 데이터를 가공
  - ItemWriter: B 테이블에 가공된 정보를 밀어넣기
