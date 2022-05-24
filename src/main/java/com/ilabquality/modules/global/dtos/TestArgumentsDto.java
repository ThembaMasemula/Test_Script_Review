package com.ilabquality.modules.global.dtos;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestArgumentsDto implements Serializable {
  private static final Gson gson = new Gson();
  private static final long serialVersionUID = 1L;

  private String name;
  private String id;
  private String row;

  public JsonElement toJsonElement() {
    return gson.toJsonTree(this,TestArgumentsDto.class);
  }
}
