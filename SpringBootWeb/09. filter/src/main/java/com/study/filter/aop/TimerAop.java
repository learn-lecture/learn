package com.study.filter.aop;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.study.filter.request.UserRequest;

@Aspect
@Component
public class TimerAop {

	@Pointcut(value = "within(com.study.filter.controller.UserApiController)")
	public void timerPointCut() {}

	@Before(value = "timerPointCut()")
	public void before(final JoinPoint joinPoint) {
		System.out.println("before");
	}

	@After(value = "timerPointCut()")
	public void after(final JoinPoint joinPoint) {
		System.out.println("after");
	}

	@AfterReturning(value = "timerPointCut()", returning = "result")
	public void afterReturning(final JoinPoint joinPoint, final Object result) {
		System.out.println("afterReturning");
	}
	@AfterThrowing(value = "timerPointCut()", throwing = "throwable")
	public void afterThrowing(final JoinPoint joinPoint, final Throwable throwable) {
		System.out.println("afterReturning");
	}

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
