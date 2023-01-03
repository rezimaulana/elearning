export const Input = (props) => {
    return (
        <>
            <label htmlFor={props.htmlFor} className={props.classNameLabel}>{props.label}</label>
            <input type={props.type} className={props.classNameInput} id={props.id} name={props.name} onChange={props.onChange}/>
        </>
    );
};
