package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.FarmProduct;
import cn.nchu.green_farm.service.exception.InsertException;

public interface IFarmProductService {
FarmProduct create(FarmProduct farmProduct) throws InsertException;

}
