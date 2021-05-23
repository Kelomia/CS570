The complexity.java 's code has mistake in method 4,5 and 6:
method4(N) calculate the O(n logn). The loop part with wrong condition, which is fixed.
method5(N) calculate the O(log logn). The space bound is reduced wrong as n/4 each time, which now is i*i each time.
method6(N) calculate the O(2^n). The old one return a fibonacci time complexity. Change the return value as:
	return method6(n-1)+method(n-1)  To method6(n) is correct.