package com.naumdeveloper.pool;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.naumdeveloper.base.BaseSprite;

import java.util.ArrayList;
import java.util.List;

//БАЗОВЫЙ КЛАСС ДЛЯ ПУЛОВ СПРАЙТОВ.
public abstract class SpritesPool<T extends BaseSprite> {

    protected  final List<T> activeObjects = new ArrayList<>();
    protected  final List<T> freeObjects = new ArrayList<>();

    protected abstract T newObject();

    public T obtain(){
        T object;
        if(freeObjects.isEmpty()){
            object = newObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        activeObjects.add(object);
        return object;
    }

    public void updateActiveObjects(float delta){
        for (T object : activeObjects){
            if(!object.isDestroyed()){
                object.update(delta);
            }
        }
    }

    public void drawActiveObjects(SpriteBatch batch){
        for (T object : activeObjects){
            if(!object.isDestroyed()){
                object.draw(batch);
            }
        }
    }

    public void freeAllDestroyed(){
        for(int i = 0; i < activeObjects.size(); i++){
            T object =activeObjects.get(i);
            if(object.isDestroyed()){
                free(object);
                i--;
            }
        }
    }

    public List<T> getActiveObjects(){
        return activeObjects;
    }

    public void dispose(){
        activeObjects.clear();
        freeObjects.clear();
    }

    public void freeAllActiveObjects(){
        freeObjects.addAll(activeObjects);
        activeObjects.clear();
    }

    private void free(T object){
        object.flushDestroy();
        if (activeObjects.remove(object)){
            freeObjects.add(object);
        }
    }
}
