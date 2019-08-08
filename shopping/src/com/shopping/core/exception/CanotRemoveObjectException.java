package com.shopping.core.exception;


public class CanotRemoveObjectException extends RuntimeException
{
  private static final long serialVersionUID = 1L;

  public void printStackTrace()
  {
    System.out.println("ɾ��������!");
    super.printStackTrace();
  }
}