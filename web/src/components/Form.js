import React, {useState} from 'react';
import {useDispatch, useSelector} from "react-redux";
import {generateList} from "../redux/listDuck";


export default function Form() {
    const ss = useSelector(state => state);
    const dispatch = useDispatch();
    const [lista, setLista] = useState('');


    const onSubmit = (e) => {
        e.preventDefault();
        dispatch(generateList(lista));
    }

    return (
        <div className="mt-2">
            <form onSubmit={onSubmit}>
                <label htmlFor="list" className="fs-3">Ingrese una lista separada por
                    comas:</label>
                <br/>
                <textarea id="list" style={{width: "300px", height: "120px"}}
                          onChange={(e) => setLista(e.target.value)}
                ></textarea>
                <br/>
                <button type="submit" className="btn btn-primary">
                    Enviar
                </button>
            </form>
        </div>
    );
}