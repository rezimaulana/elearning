export const Button = (props) => {
  return (
    <button type={props.type} className={props.className} id={props.id} name={props.name} onClick={props.onClick}>
      {props.label}
    </button>
  );
};
