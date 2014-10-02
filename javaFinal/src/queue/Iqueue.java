package queue;

import customer.Customer;

public interface Iqueue {
	
	//customer dequeue
	public abstract Customer dequeue();
	//customer enqueue
	public abstract void enqueue(Customer c1);
	//customer를 dequeue하지는 않고 리턴만 함
	public abstract Customer getCustomer(int i);
	//queue가 비어있는지 확인
	public abstract boolean isEmpty();
	//queue에 들어있는 customer의 수
	public abstract int getSize();
	//큐에 들어있는 customer들의 time status중 i 번째 옵션의 값을 1증
	public abstract void increaseCustomerTimeInQueue(int i);
}
