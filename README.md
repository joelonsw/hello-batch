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

#### 5. 파일에서 값 읽어오고 작성하기
- csv 파일에서 , 로 값을 파싱하여 자바 클래스로 만들고, 이를 다시 csv 파일로 기록할 수 있다
- FlatFileItemReader/FlatFileItemWriter에서 제공하는 여러 기능들을 통해 해당 기능 구현 가능
  - lineTokenizer
  - fieldSetMapper
  - linesToSkip
  - BeanWrapperFieldExtractor
  - DelimitedLineAggregator
  - FileSystemResource

#### 6. 하나의 Job에 여러 Step 등록하기
- Job에 Step 여러개를 등록할 수 있다
  - `.start(스텝1).next(스텝2).next(스텝3).build()` 
  - 이렇게 등록한 순차적인 Step에 이전 Step의 데이터를 `ExecutionContext`에 데이터를 `.put()`, `.get()`을 통해 전달할 수 있다.
- Job의 Step의 결과값에 따라 차후 실행할 Step을 지정할 분기처리도 가능하다. 
  - `.start(원스텝).on(A상황).to(A스텝).on(B상황).to(B스텝)`

#### 7. 테스트 코드 작성하기
- 기존 Junit과 JobLauncherTestUtils를 활용하여 배치잡을 돌릴 수 있는 환경을 테스트에서 구축할 수 있다.

#### 8. Scheduler를 통한 Job Cron 등록
- JobLauncher를 통해 코드레벨에서 배치 Job을 실행 시킬 수 있음
- 이때, 스케줄러로 실행시키고자 하는 Job을 메서드로 분리해, `@Scheduled`를 활용해 돌아가게 만들어두자!
