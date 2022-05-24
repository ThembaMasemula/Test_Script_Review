package com.ilabquality.modules.global.utils;

import com.ilabquality.modules.global.reference.SystemDefault;
import org.burningwave.core.assembler.ComponentContainer;
import org.burningwave.core.assembler.ComponentSupplier;
import org.burningwave.core.classes.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClassFinder {

  private final SystemDefault defaults = SystemDefault.getInstance();

  public List<Class<?>> findClasses(List<String> testNames) {
    List<Class<?>> response = new LinkedList<>();

    ComponentSupplier componentSupplier = ComponentContainer.getInstance();
    ClassHunter classHunter = componentSupplier.getClassHunter();

    String TEST_PACKAGE = defaults.TEST_PACKAGE;
    CacheableSearchConfig config = SearchConfig
      .forResources(TEST_PACKAGE);

    for (String testName: testNames) {
      ClassCriteria criteria = ClassCriteria.create().allThoseThatMatch(clazz -> {
        String className = clazz.getSimpleName();
        return className.matches(testName);
      });

      SearchResult<Class<?>> result = classHunter.findBy(config);
      Map<String, Class<?>> resultClasses = result.getClasses(criteria);

      response.addAll(resultClasses.values());
    }

    return response;
  }
}
