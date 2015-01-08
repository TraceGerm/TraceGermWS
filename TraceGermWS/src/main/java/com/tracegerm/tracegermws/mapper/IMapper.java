package com.tracegerm.tracegermws.mapper;

public interface IMapper <S, T>{
	
	T map(S source, T target);

}
