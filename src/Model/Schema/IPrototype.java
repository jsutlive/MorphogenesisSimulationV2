package Model.Schema;

import Engine.Object.MonoBehavior;

import java.util.List;

public interface IPrototype {
    List<MonoBehavior> Generate() throws InstantiationException, IllegalAccessException;
}
