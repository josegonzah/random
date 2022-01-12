import React, {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {getList} from "../redux/listDuck";

export default function Result() {
    const results = useSelector(state => state.randomList.list);
    const dispatch = useDispatch();
    let lista = [];
    useEffect(() => {
    }, [results]);


    const handleClick = () => {
        dispatch(getList());
    }

    return (
        <>
            <button onClick={() => handleClick()}
                    className="btn btn-primary mt-2"
            >Todos los resultados
            </button>
            <br/>
            Resultado:
            <br/>
            <table className="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Result</th>

                </tr>
                </thead>
                <tbody>
                {
                    results?.map((result, index) => (
                            <tr key={result.id}>
                                <th scope="row">{index}</th>
                                <td>{result.randomList}</td>

                            </tr>
                        )
                    )
                }

                </tbody>
            </table>

        </>
    )
}