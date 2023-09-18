import {combineReducers, configureStore} from '@reduxjs/toolkit'
import {setupListeners} from "@reduxjs/toolkit/query";
import storage from 'redux-persist/lib/storage';
import {persistReducer, persistStore} from 'redux-persist';
import thunk from 'redux-thunk';

const persistConfig = {
  key: 'root',
  storage,
}

const rootReducer = combineReducers({

})

const persistedReducer = persistReducer(persistConfig, rootReducer)

export const store = configureStore({
  reducer: persistedReducer,
  middleware: [thunk].concat(

  )
})

setupListeners(store.dispatch)

export const persistor = persistStore(store)