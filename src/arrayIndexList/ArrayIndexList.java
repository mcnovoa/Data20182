package arrayIndexList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import indexList.IndexList;

public class ArrayIndexList<E> implements IndexList<E> {
	private static final int INITCAP = 1; 
	private static final int CAPTOAR = 1; 
	private static final int MAXEMPTYPOS = 2; 
	private E[] element; 
	private int size; 

	public ArrayIndexList() { 
		element = (E[]) new Object[INITCAP]; 
		size = 0; 
	} 


	public void add(int index, E e) throws IndexOutOfBoundsException {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException(" add: invalid index = " + index);
		if(this.size == this.element.length)
			changeCapacity(CAPTOAR);

		this.moveDataOnePositionTR(index, size-1);
		this.element[index] = e;
		size++;

	}


	public void add(E e) {
		if(this.size() == element.length)
			changeCapacity(CAPTOAR);
		else {
			this.element[size] = e;
			size++;
		}
	}


	public E get(int index) throws IndexOutOfBoundsException {
		// ADD AND MODIGY CODE AS REQUESTED BY EXERCISES
		if(this.isEmpty())	
			return null;
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("get: invalid index = " + index);

		return this.element[index];
	}


	public boolean isEmpty() {
		return size == 0;
	}


	public E remove(int index) throws IndexOutOfBoundsException {
		// ADD AND MODIFY CODE AS REQUESTED BY EXERCISES
		if(this.isEmpty())
			return null;
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("remove: invalid index = " + index);

		E result = this.element[index];
		this.moveDataOnePositionTL(index + 1, size - 1);
		this.element[this.element.length - 1] = null;
		size--;
		return result;

	}


	public E set(int index, E e) throws IndexOutOfBoundsException {
		// ADD AND MODIFY CODE AS REQUESTED BY EXERCISES
		if(this.isEmpty())
			return null;
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("set: invalid index = " + index);

		E result = this.element[index];
		this.element[index] = e;
		return result;

	}


	public int size() {
		return size;
	}	



	// private methods  -- YOU CAN NOT MODIFY ANY OF THE FOLLOWING
	// ... ANALYZE AND USE WHEN NEEDED

	// you should be able to decide when and how to use
	// following method.... BUT NEED TO USE THEM WHENEVER
	// NEEDED ---- THIS WILL BE TAKEN INTO CONSIDERATION WHEN GRADING

	private void changeCapacity(int change) { 
		int newCapacity = element.length + change; 
		E[] newElement = (E[]) new Object[newCapacity]; 
		for (int i=0; i<size; i++) { 
			newElement[i] = element[i]; 
			element[i] = null; 
		} 
		element = newElement; 
	}

	// useful when adding a new element with the add
	// with two parameters....
	private void moveDataOnePositionTR(int low, int sup) { 
		// pre: 0 <= low <= sup < (element.length - 1)
		for (int pos = sup; pos >= low; pos--)
			element[pos+1] = element[pos]; 
	}

	// useful when removing an element from the list...
	private void moveDataOnePositionTL(int low, int sup) { 
		// pre: 0 < low <= sup <= (element.length - 1)
		for (int pos = low; pos <= sup; pos++)
			element[pos-1] = element[pos]; 
	}


	// The following two methods are to be implemented as part of an exercise
	public Object[] toArray() {
		// TODO es in Exercise 3
		Object[] result = new Object[size];

		for (int i = 0; i < this.size; i++) 
			result[i] = this.element[i];

		return result;
	}


	@Override
	public <T1> T1[] toArray(T1[] array) {
		if (array.length < this.size()) { 
			array = (T1[]) Array.newInstance(array.getClass().getComponentType(), this.size());
		} 
		if (array.length > this.size())  
			for (int i = this.size(); i< array.length; i++) 
				array[i] = null;

		for (int k = 0; k < this.size(); k++) {
			array[k] = (T1) element[k];   
		}
		return array; 
	}

	@Override
	public int capacity() {
		return this.element.length;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			this.element[i] = null;
			size--;
		}
	}

	public Object clone() {
		Object result = new ArrayIndexList();

		for (int i = 0; i < size; i++) {
			((IndexList<E>) result).add(this.element[i]);
		}

		return result;
	}

	public List<E> subList(int fromIndex, int toIndex){
		List<E> result = new ArrayList();

		for (int i = fromIndex; i <= toIndex; i++) {
			result.add(this.element[i]);
		}

		return result;
	}
}
