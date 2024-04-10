package com.study.filter.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.study.filter.request.UserRequest;

@Aspect
@Component
public class TimerAop {

	@Pointcut(value = "within(com.study.filter.controller.UserApiController)")
	public void timerPointCut() {}

	@Around(value = "timerPointCut()")
	public void around(final ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("메소드 실행 이전");
		Arrays.stream(joinPoint.getArgs()).forEach(
			it -> {
				if(it instanceof UserRequest) {
					final UserRequest tempUser = (UserRequest)it;
					final String phone = tempUser.getPhoneNumber().replace("-","");
					tempUser.setPhoneNumber(phone);
				}
			}
		);

		// 암/복호화 or 로깅 데이터를 전달할 수 있음.
		final List<UserRequest> list = Arrays.asList(new UserRequest());

		// 한 프로시드의 시간 측정
		final StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		joinPoint.proceed(list.toArray());

		stopWatch.stop();
		System.out.println("총 소요 시간 " + stopWatch.getTotalTimeMillis() + "ms");

		System.out.println("메소드 실행 이후");
	}

}
