package com.team2502.lib;

import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

public class Pair<L, R>
{
	public L left;
	public R right;

	private String nameL;
	private String nameR;

	public Pair(L left, R right)
	{
		this.left = left;
		this.right = right;
		this.nameL = left.getClass().getSimpleName();
		this.nameR = right.getClass().getSimpleName();
	}

	public Pair()
	{
	}

	@Override
	public String toString()
	{
		return new StringBuilder(100 + nameL.length() + nameR.length()).append("Pair<").append(nameL).append(',')
				.append(nameR).append("> { \"left\": \"").append(left).append("\", \"right\": \"").append(right)
				.append("\" }").toString();
	}

	@Override
	public int hashCode()
	{
		return left.hashCode() * 13 + (right == null ? 0 : right.hashCode());
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o instanceof Pair)
		{
			Pair pair = (Pair) o;
			return (left != null ? left.equals(pair.left) : pair.left == null)
					&& (left != null ? left.equals(pair.left) : pair.left == null);
		}
		return false;
	}
}